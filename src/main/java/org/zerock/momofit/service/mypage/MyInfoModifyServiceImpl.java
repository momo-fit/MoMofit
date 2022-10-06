package org.zerock.momofit.service.mypage;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.zerock.momofit.exception.ServiceException;
import org.zerock.momofit.mapper.mypage.MyInfoModifyMapper;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;


@Log4j2
@AllArgsConstructor

@Service
public class MyInfoModifyServiceImpl implements MyInfoModifyService {
	
	private MyInfoModifyMapper myInfoModifyMapper;
	
	// 1. Password 유효성 CHECK
	@Override
	public boolean checkValidPass(String pass, int user_no) throws ServiceException {
		log.trace("checkValidPass() invoked.");
		
		try {
			
			// password 복호화
			BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
			
			String salt = "_SALT"; 
			
			String password = this.myInfoModifyMapper.selectUserPass(user_no);
			
			return encoder.matches(salt + pass, password);
					
		} catch (Exception e) {
			throw new ServiceException(e);
		} // try-catch
		
	} // checkValidPass

	
	
	
} // end class
