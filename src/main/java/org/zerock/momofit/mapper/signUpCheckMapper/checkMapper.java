package org.zerock.momofit.mapper.signUpCheckMapper;

import org.zerock.momofit.exception.DAOException;

public interface checkMapper {
	
	public abstract String idCheck(String id) throws DAOException ;
	
	public abstract String nickNameCheck(String nickname) throws DAOException ;
	
} // end interface
