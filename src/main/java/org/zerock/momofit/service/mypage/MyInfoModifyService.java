package org.zerock.momofit.service.mypage;

import org.springframework.web.multipart.MultipartFile;
import org.zerock.momofit.domain.signIn.LoginVO;
import org.zerock.momofit.domain.signUp.UserDTO;
import org.zerock.momofit.exception.ServiceException;


public interface MyInfoModifyService {

	// 1. Password 유효성 CHECK
	public abstract boolean checkValidPass(String pass, int user_no) throws ServiceException;
	
	// 2. 회원정보 수정
	public abstract boolean modifyUserInfo(UserDTO dto, LoginVO vo, MultipartFile file) throws ServiceException;
	
	// 3. 회원정보 불러오기
	public abstract LoginVO getUserInfo(int user_no) throws ServiceException;
	
} // end class
