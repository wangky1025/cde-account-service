package io.cde.account.controller;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.cde.account.domain.ErrorInfo;
import io.cde.account.domain.Mobile;
import io.cde.account.domain.i18n.Error;
import io.cde.account.exception.AccountNotFoundException;
import io.cde.account.exception.BizException;
import io.cde.account.service.impl.MobileServiceImpl;
import io.cde.account.tools.ErrorMessageSourceHandler;
import io.cde.account.tools.RegexUtils;

/**
 * @author lcl
 */
@RestController
@RequestMapping(value = "/accounts")
public class MobileController {

    /**
     * 记录日志.
     */
    private final Logger logger = LoggerFactory.getLogger(Mobile.class);

    /**
     * ErrorMessageSourceHandler对象.
     */
    private ErrorMessageSourceHandler errorHandler;

    /**
     * MobileServiceImpl对象.
     */
    private MobileServiceImpl mobileService;

    /**
     * 通过构造器注入对象.
     *
     * @param errorHandler errorHandler对象
     * @param mobileService mobileService对象
     */
    @Autowired
    public MobileController(final ErrorMessageSourceHandler errorHandler, final MobileServiceImpl mobileService) {
        this.errorHandler = errorHandler;
        this.mobileService = mobileService;
    }

    /**
     * 获取用户电话信息.
     *
     * @param accountId 用户id
     * @return 成功则返回用户电话信息，操作错误则返回相应的错误信息
     */
    @RequestMapping(value = "/{accountId}/mobiles", method = RequestMethod.GET)
    public List<Mobile> getMobiles(@PathVariable final String accountId) {
        final List<Mobile> mobiles;
        logger.info("get the account's mobiles started");
        try {
            mobiles = mobileService.getMobiles(accountId);
        } catch (BizException e) {
            logger.debug("get the account's mobiles failed", e);
            throw new AccountNotFoundException();
        }
        return mobiles;
    }

    /**
     * 修改用户的电话信息.
     *
     * @param accountId 用户id
     * @param mobileId  要修改的电话的id
     * @param params 接收参数的map
     * @return 返回操作的结果
     */
    @RequestMapping(value = "/{accountId}/mobiles/{mobileId}", method = RequestMethod.POST)
    public ErrorInfo updateMobile(@PathVariable final String accountId, @PathVariable final String mobileId, @RequestBody final Map<String, Object> params) {
        logger.info("update mobile started");
        try {
            if (params.get("verified") != null) {
                mobileService.updateMobile(accountId, mobileId, (Boolean) params.get("verified"));
                return new ErrorInfo();
            }
            if (params.get("public") != null) {
                mobileService.updatePublicMobile(accountId, (Boolean) params.get("public"));
                return new ErrorInfo();
            }
        } catch (BizException e) {
            logger.debug("update mobile failed", e);
            return this.handException(e);
        }
        return new ErrorInfo(Error.MISS_REQUIRED_PARAMETER.getCode(), errorHandler.getMessage(Error.MISS_REQUIRED_PARAMETER.toString()));
    }

    /**
     * 增加用户电话信息.
     *
     * @param accountId 用户id
     * @param params 接收参数的map
     * @return 返回添加操作的结果
     */
    @RequestMapping(value = "/{accountId}/mobiles", method = RequestMethod.POST)
    public ErrorInfo addMobile(@PathVariable final String accountId, @RequestBody final Map<String, String> params) {
        if (params.get("mobile") == null) {
            return new ErrorInfo(Error.MISS_REQUIRED_PARAMETER.getCode(), errorHandler.getMessage(Error.MISS_REQUIRED_PARAMETER.toString()));
        }
        if (!RegexUtils.isMobile(params.get("mobile"))) {
            return new ErrorInfo(Error.ILLEGAL_MOBILE.getCode(), errorHandler.getMessage(Error.ILLEGAL_MOBILE.toString()));
        }
        logger.info("add mobile started");
        final Mobile mobile = new Mobile();
        mobile.setMobile(params.get("mobile"));
        try {
            mobileService.addMobile(accountId, mobile);
        } catch (BizException e) {
            logger.debug("add mobile failed", e);
            return this.handException(e);
        }
        return new ErrorInfo();
    }

    /**
     * 删除用户电话信息.
     *
     * @param accountId 用户id
     * @param mobileId  要删除的电话的id
     * @return 删除操作的结果
     */
    @RequestMapping(value = "/{accountId}/mobiles/{mobileId}", method = RequestMethod.DELETE)
    public Object deleteMobile(@PathVariable final String accountId, @PathVariable final String mobileId) {
        logger.info("delete mobile started");
        try {
            mobileService.deleteMobile(accountId, mobileId);
        } catch (BizException e) {
            logger.debug("delete mobile failed", e);
            return this.handException(e);
        }
        return new ErrorInfo();
    }

    /**
     * 异常处理.
     *
     * @param e catch到的异常
     * @return 若是AccountNotFundException则抛出，否则返回异常对象信息
     */
    private ErrorInfo handException(final BizException e) {
        if (e.getCode() == Error.INVALID_ACCOUNT_ID.getCode()) {
            throw new AccountNotFoundException();
        }
        return new ErrorInfo(e.getCode(), e.getMessage());
    }
}
