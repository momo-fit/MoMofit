package org.zerock.momofit.service.find;

import org.zerock.momofit.exception.ServiceException;

public interface findService {
	
	// 아이디 찾기
	public abstract String selectFindId(String email) throws ServiceException;
	
	// 비밀번호 찾기
	public abstract boolean selectFindPw(String email, String id) throws ServiceException;
}
