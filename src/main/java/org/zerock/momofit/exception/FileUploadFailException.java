package org.zerock.momofit.exception;

public class FileUploadFailException extends Exception {
	private static final long serialVersionUID = 1L;
	

	public FileUploadFailException(String message) {
		super(message);
	} // constructor
	

	public FileUploadFailException(Exception e) {
		super(e);
	} // constructor

} // end class
