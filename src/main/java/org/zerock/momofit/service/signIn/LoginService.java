package org.zerock.momofit.service.signIn;

import java.sql.Timestamp;

import org.zerock.momofit.domain.signIn.LoginDTO;
import org.zerock.momofit.domain.signIn.LoginVO;
import org.zerock.momofit.exception.ServiceException;

public interface LoginService {
	
	// 로그인
	public abstract LoginVO userLoginService(LoginDTO dto) throws ServiceException;
	
	// 자동 로그인 확인
	public abstract boolean modifyUserRememberMe(String id, String rememberme, Timestamp rememberage) throws ServiceException;
	
	public abstract LoginVO seleteRememberMeCookie(String rememberMeCookie) throws ServiceException;
	
}
