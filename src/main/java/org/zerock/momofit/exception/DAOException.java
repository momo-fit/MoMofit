package org.zerock.momofit.exception;


public class DAOException extends Exception {	
	private static final long serialVersionUID = 1L;
	

	public DAOException(String message) {
		super(message);
	} // constructor
	
	public DAOException(Exception e) {
		super(e);
	} // constructor


} // end class
