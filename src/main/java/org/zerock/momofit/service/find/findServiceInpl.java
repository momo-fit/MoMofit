package org.zerock.momofit.service.find;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.zerock.momofit.exception.ServiceException;
import org.zerock.momofit.mapper.findMapper.findMappers;
import org.zerock.momofit.service.signUp.mail.MailService;

import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;

@Log4j2
@NoArgsConstructor

@Service
public class findServiceInpl implements findService {

	@Setter(onMethod_ = {@Autowired})
	findMappers mapper;
	
	@Setter(onMethod_ = {@Autowired})
	MailService mailService;
	
	@Override
	public String selectFindId(String email) throws ServiceException {
		log.trace("selectFindId({}) invoked.", email);
		
		try {
			String resultId = mapper.idFindSelect(email);
			return resultId;
		} catch (Exception e) {
			throw new ServiceException(e);
		} // try-catch
		
	}

	@Override
	public boolean selectFindPw(String email, String id) throws ServiceException {
		log.trace("selectFindId({}, {}) invoked.", email, id);
		
		try {
			String resultPw = mapper.pwFindSelect(email, id);
			
			if(resultPw != null) {
				
				
				this.mailService.pwMailSend(email, id);
				return true;
			} else {
				return false;
			} // else-if
			
		} catch (Exception e) {
			throw new ServiceException(e);
		} // try-catch

	} // selectFindId

} // end class
