package org.zerock.momofit.mapper.findMapper;

import org.apache.ibatis.annotations.Param;
import org.zerock.momofit.exception.DAOException;

public interface findMappers {
	
	// 아이디 찾기
	public abstract String idFindSelect(String email) throws DAOException;
	
	// 비밀번호 찾기
	public abstract String pwFindSelect(@Param("email")String email, @Param("id")String id) throws DAOException;
	
	// 임시비밀번호
	public abstract void tempPwUpdate(@Param("id")String id, @Param("pass")String pass) throws DAOException;
	
	// 이메일 확인
	public abstract String emailCheck(String email) throws DAOException;
		
	
} // end class
