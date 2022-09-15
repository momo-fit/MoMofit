package org.zerock.momofit.service.signUpCheck.idAndPw;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.zerock.momofit.exception.ServiceException;
import org.zerock.momofit.mapper.signUpMapper.checkMapper;

import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;

@Log4j2
@NoArgsConstructor

@Service
public class signUpCheckServiceImpl implements signUpCheckService {
	
	@Setter(onMethod_ = {@Autowired})
	private checkMapper mapper;
	
	@Override
	public boolean idCheck(String id) throws ServiceException {
		log.trace("idCheck({}) invoked." , id);
		
		try {
			String idResult = this.mapper.idCheck(id);
			
			if(idResult != null) {
				return false;
			}
		}catch (Exception e) {
			throw new ServiceException(e);
		} // try-catch
		
		
		return true;
	} // idCheck

	@Override
	public boolean nickNameCheck(String nickname) throws ServiceException {
		log.trace("nickNameCheck({}) invoked." , nickname);
		
		try {
			String nickNameResult = this.mapper.nickNameCheck(nickname);
			
			if(nickNameResult != null) {
				return false;
			}
		}catch (Exception e) {
			throw new ServiceException(e);
		} // try-catch
		
		return true;
	} // nickNameCheck
	
} // end class
