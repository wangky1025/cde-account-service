package io.cde.account.controller;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.cde.account.domain.ErrorInfo;
import io.cde.account.domain.Mobile;
import io.cde.account.domain.i18n.Error;
import io.cde.account.exception.AccountNotFoundException;
import io.cde.account.exception.BizException;
import io.cde.account.service.impl.MobileServiceImpl;
import io.cde.account.tools.RegexUtils;

/**
 * @author lcl
 *
 */
@RestController
@RequestMapping(value = "/accounts")
public class MobileController {
	
	/**
	 * 记录日志.
	 */
	private final Logger logger = LoggerFactory.getLogger(Mobile.class);
    
	@Autowired
	private MobileServiceImpl mobileService;
	
	/**
	 * 获取用户电话信息.
	 * 
	 * @param accountId 用户id
	 * @return 成功则返回用户电话信息，操作错误则返回相应的错误信息
	 */
	@RequestMapping(value = "/{accountId}/mobiles", method = RequestMethod.GET)
	public List<Mobile> getMobiles(@PathVariable String accountId) {
        List<Mobile> mobiles = new ArrayList<>();
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
	 * @param mobileId 要修改的电话的id
	 * @param mobile 携带要修改的电话信息的对象
	 * @return 返回操作的结果
	 */
	@RequestMapping(value = "/{accountId}/mobiles/{mobileId}", method = RequestMethod.POST)
	public ErrorInfo updateMobile(@PathVariable String accountId, @PathVariable String mobileId, @RequestParam(name = "isVerified") boolean isVerified) {
		logger.info("update mobile started");
		try {
			mobileService.updateMobile(accountId, mobileId, isVerified);
		} catch (BizException e) {
			logger.debug("update mobile failed", e);
			return this.handException(e);
		}
		return null;
	}
	
	/**
	 * 增加用户电话信息.
	 * 
	 * @param accountId 用户id
	 * @param mobile 携带要添加的电话的结果
	 * @return 返回添加操作的结果
	 */
	@RequestMapping(value = "/{accountId}/mobiles", method = RequestMethod.POST)
	public ErrorInfo addMobile(@PathVariable String accountId, @RequestParam(name = "mobile") String mobile) {
        System.err.println("--------检测电话号码正则表达式----------" + mobile + "*****" + RegexUtils.isMobile(mobile));
		logger.info("add mobile started");
		Mobile mobile2 = new Mobile();
		mobile2.setMobile(mobile);
		try {
			mobileService.addMobile(accountId, mobile2);
		} catch (BizException e) {
			logger.debug("add mobile failed", e);
			return this.handException(e);
		}
		return null;
	}
	
	/**
	 * 删除用户电话信息.
	 * 
	 * @param accountId 用户id
	 * @param mobileId 要删除的电话的id
	 * @return 删除操作的结果
	 */
	@RequestMapping(value = "/{accountId}/mobiles/{mobileId}", method = RequestMethod.DELETE)
	public Object deleteMobile(@PathVariable String accountId, @PathVariable String mobileId) {
        logger.info("delete mobile started");
		try {
			mobileService.deleteMobile(accountId, mobileId);
		} catch (BizException e) {
			logger.debug("delete mobile failed", e);
			return this.handException(e);
		}
		return null;
	}
	
	/**
	 * 异常处理.
	 * 
	 * @param e catch到的异常
	 * @return 若是AccountNotFundException则抛出，否则返回异常对象信息
	 */
	private ErrorInfo handException(BizException e) {
		if (e.getCode() == Error.INVALID_ACCOUNT_ID.getCode()) {
			throw new AccountNotFoundException();
		}
		return new ErrorInfo(e.getCode(), e.getMessage());
	}
}
