package org.zerock.momofit.service.signIn;

import java.sql.Timestamp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.zerock.momofit.domain.signIn.LoginDTO;
import org.zerock.momofit.domain.signIn.LoginVO;
import org.zerock.momofit.exception.DAOException;
import org.zerock.momofit.exception.ServiceException;
import org.zerock.momofit.mapper.signInMapper.signInMapper;

import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;

@Log4j2
@NoArgsConstructor

@Service
public class LoginServiceImpl implements LoginService{
	
	@Setter(onMethod_ = {@Autowired})
	private signInMapper mapper;
	
	@Override
	public LoginVO userLoginService(LoginDTO dto) throws ServiceException {
		log.trace("LoginService({}) invoked.", dto);
		
		// pw 암호화
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		String salt = "_SALT";
		
		try {
			String result = mapper.selectUserPass(dto);
			
			boolean isMatches = encoder.matches(salt+dto.getPass(), result);
			log.info("\t+ isMatches: {}", isMatches);
			
			if(isMatches) {
				
				return this.mapper.selectUser(dto);
			} else {
				return null;
			} // else-if
			
			
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
		
		
	} // userLoginService

	@Override
	public boolean modifyUserRememberMe(String id, String rememberme, Timestamp rememberage) throws ServiceException {
		log.trace("LoginService({}, {}, {}) invoked.", id, rememberme, rememberage);
		
		try {
			return this.mapper.updateRememberMe(id, rememberme, rememberage) == 1;
		}catch (Exception e) {
			throw new ServiceException(e);
		}
		
	} // modifyUserRememberMe

	@Override
	public LoginVO seleteRememberMeCookie(String rememberMeCookie) throws ServiceException {
		log.trace("seleteRememberMeCookie({}) invoked.", rememberMeCookie);
		try {
			return this.mapper.selectRememberMeCookie(rememberMeCookie);
		}catch (Exception e) {
			throw new ServiceException(e);
		}

	} // seleteRememberMeCookie

} // end class
