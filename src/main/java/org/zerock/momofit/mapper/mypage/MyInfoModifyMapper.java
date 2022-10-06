package org.zerock.momofit.mapper.mypage;

import org.apache.ibatis.annotations.Param;
import org.zerock.momofit.domain.signIn.LoginVO;
import org.zerock.momofit.domain.signUp.UserDTO;
import org.zerock.momofit.exception.DAOException;

public interface MyInfoModifyMapper {

	// 1. PW가져오기
	public abstract String selectUserPass(@Param("user_no") int user_no) throws DAOException;
	
	// 2. 회원정보수정
	public abstract Integer updateUserInfo(UserDTO dto) throws DAOException;
	
	// 3. 회원정보 불러오기
	public abstract LoginVO selectUserInfo(@Param("user_no") int user_no) throws DAOException;
	
} // end interface
