package org.zerock.momofit.mapper.mypage;

import org.apache.ibatis.annotations.Param;
import org.zerock.momofit.exception.DAOException;

public interface MyInfoModifyMapper {

	// 1. PW가져오기
	public abstract String selectUserPass(@Param("user_no") int user_no) throws DAOException;
	
} // end interface
