package org.zerock.momofit.mapper.signInMapper;

import java.sql.Timestamp;

import org.apache.ibatis.annotations.Param;
import org.zerock.momofit.domain.signIn.LoginDTO;
import org.zerock.momofit.domain.signIn.LoginVO;
import org.zerock.momofit.exception.DAOException;

public interface signInMapper {
	
	// 사용자 조회
	public abstract LoginVO selectUser(LoginDTO dto) throws DAOException;
	
	// 사용자 조회
	public abstract String selectUserPass(LoginDTO dto) throws DAOException;
	
	// 자동로그인 확인
	public abstract int updateRememberMe( @Param("id")String id, @Param("rememberme")String rememberme, @Param("rememberage")Timestamp rememberage) 
			throws DAOException;
	
	public abstract LoginVO selectRememberMeCookie(@Param("rememberme")String rememberMeCookie) throws DAOException;
	
} // end interface
