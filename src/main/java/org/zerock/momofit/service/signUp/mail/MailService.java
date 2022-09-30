package org.zerock.momofit.service.signUp.mail;

import org.springframework.mail.MailSendException;
import org.zerock.momofit.exception.ServiceException;

public interface MailService {
	
	// 메일 인증
	public abstract String mailSend(String email) throws MailSendException;
	
	// 비밀번호 찾기
	public abstract void pwMailSend(String email, String id) throws MailSendException, ServiceException;

} // end interface
