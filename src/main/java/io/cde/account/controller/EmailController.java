package io.cde.account.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.cde.account.domain.Email;
import io.cde.account.domain.ErrorInfo;
import io.cde.account.domain.i18n.Error;
import io.cde.account.exception.AccountNotFundException;
import io.cde.account.exception.BizException;
import io.cde.account.service.impl.EmailServiceImpl;

/**
 * @author lcl
 * @createDate 2016年12月1日下午6:30:28
 *
 */
@RestController
@RequestMapping(value = "/accounts")
public class EmailController {
    
	@Autowired
	private EmailServiceImpl emailService;
	
	/**
	 * 获取用户的邮箱信息.
	 * 
	 * @param accountId 用户id
	 * @return 返回用户的邮箱信息或是错误的操作信息
	 */
	@RequestMapping(value = "/{accountId}/emails", method = RequestMethod.GET)
	public List<Email> getEmails(@PathVariable String accountId) {
		List<Email> emails = new ArrayList<>();
	    try {
	    	emails = emailService.getEmails(accountId);
		} catch (BizException e) {
			throw new AccountNotFundException();
		}
		return emails;
	}
	/**
	 * 修改用户的邮箱信息.
	 * 
	 * @param accountId 用户id
	 * @param emailId 要修改的邮箱id
	 * @param email 携带要修改的邮箱信息的对象
	 * @return 返回修改操作的结果
	 */
	@RequestMapping(value = "/{accountId}/emails/{emailId}", method = RequestMethod.POST)
	public ErrorInfo updateEmail(@PathVariable String accountId, @PathVariable String emailId, @RequestParam(name = "isVerified") boolean isVerified) {
		try {
			emailService.updateEmail(accountId, emailId, isVerified);
		} catch (BizException e) {
			return this.handException(e);
		}
		return null;
	}
	/**
	 * 添加用户邮箱信息.
	 * 
	 * @param accountId 用户id
	 * @param email 携带要添加的邮箱信息的对象
	 * @return 返回添加操作的结果
	 */
	@RequestMapping(value = "/{accountId}/emails", method = RequestMethod.POST)
	public ErrorInfo addEmail(@PathVariable String accountId, @ModelAttribute(name = "email") Email email) {
		try {
			emailService.addEmail(accountId, email);
		} catch (BizException e) {
			return this.handException(e);
		}
		return null;
	}
	/**
	 * 删除用户邮箱信息.
	 * 
	 * @param accountId 用户id
	 * @param emailId 要删除的邮箱的id
	 * @return 返回删除操作的结果
	 */
	@RequestMapping(value = "/{accountId}/emails/{emailId}", method = RequestMethod.DELETE)
	public ErrorInfo deleteEmail(@PathVariable String accountId, @PathVariable String emailId) {
		try {
			emailService.deleteEmail(accountId, emailId);
		} catch (BizException e) {
			return this.handException(e);
		}
		return null;
	}
	
	/**
	 * 异常处理
	 * @param e catch到的异常
	 * @return 若是AccountNotFundException则抛出，否则返回异常对象信息
	 */
	private ErrorInfo handException(BizException e) {
		if (e.getCode() == 100001 || e.getCode() == Error.UNASSOCIATED_ACCOUNT_AND_EMAIL.getCode()) {
			throw new AccountNotFundException();
		}
		return new ErrorInfo(e.getCode(), e.getMessage());
	}
}
