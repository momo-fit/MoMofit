package org.zerock.momofit.service.mail;

import org.springframework.mail.MailSendException;

public interface MailService {

	 public abstract String mailSend(String email) throws MailSendException;

} // end interface
