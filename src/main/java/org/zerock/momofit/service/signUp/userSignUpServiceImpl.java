package org.zerock.momofit.service.signUp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.zerock.momofit.domain.signUp.UserDTO;
import org.zerock.momofit.exception.ServiceException;
import org.zerock.momofit.mapper.signUpMapper.signUpMapper;

import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;

@Log4j2
@NoArgsConstructor

@Service
public class userSignUpServiceImpl implements userSignUpService {
	
	@Setter(onMethod_ = {@Autowired})
	private signUpMapper signUpMapper;
	

	@Override
	public boolean UserSignUp(UserDTO dto) throws ServiceException {
		log.trace("UserSignUp() invoked.");

		try {
			return this.signUpMapper.UserInsert(dto) == 1;
		}catch (Exception e) {
			throw new ServiceException(e);
		} // try-catch

	} // UserSignUp
	
	@Override
	public boolean idCheck(String id) throws ServiceException {
		log.trace("idCheck({}) invoked." , id);
		
		try {
			String idResult = this.signUpMapper.idCheck(id);
			
			if(idResult != null) {
				return false;
			} else {
				return true;
			} // if-else
			
		}catch (Exception e) {
			throw new ServiceException(e);
		} // try-catch		
		
	} // idCheck

	@Override
	public boolean nickNameCheck(String nickname) throws ServiceException {
		log.trace("nickNameCheck({}) invoked." , nickname);
		
		try {
			String nickNameResult = this.signUpMapper.nickNameCheck(nickname);
			
			if(nickNameResult != null) {
				return false;
			} else {
				return true;
			} // if-else
			
		}catch (Exception e) {
			throw new ServiceException(e);
		} // try-catch

	} // nickNameCheck

} // end class
