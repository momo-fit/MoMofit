package org.zerock.momofit.service.signUpCheck.mail;

import org.springframework.mail.MailSendException;

public interface MailService {
	
	// 메일 인증
	public abstract String mailSend(String email) throws MailSendException;

} // end interface
