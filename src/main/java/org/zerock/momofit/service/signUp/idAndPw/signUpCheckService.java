package org.zerock.momofit.service.signUpCheck.idAndPw;

import org.zerock.momofit.exception.ServiceException;

public interface signUpCheckService {
	
	// 아이디 중복 체크
	public abstract boolean idCheck(String id) throws ServiceException;
	
	// 닉네임 중복 체크
	public abstract boolean nickNameCheck(String nickname) throws ServiceException;
	
} // end interface
