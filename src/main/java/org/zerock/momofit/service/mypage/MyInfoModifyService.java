package org.zerock.momofit.service.mypage;

import org.zerock.momofit.domain.signUp.UserDTO;
import org.zerock.momofit.exception.ServiceException;


public interface MyInfoModifyService {

	// 1. Password 유효성 CHECK
	public abstract boolean checkValidPass(String pass, int user_no) throws ServiceException;
	
} // end class
