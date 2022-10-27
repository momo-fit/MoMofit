package org.zerock.momofit.common;

public final class SharedScopeKeys {
	
	public static final String LOGIN_KEY = "__LOGIN__";
	public static final String LOGIN_RESULT = "__RESULT__";
	public static final String USER_KEY = "__USER__";
	public static final String REMEMBERME_KEY = "__REMEMBERME__";
	public static final String REFERER_URL = "__REFERER__";
	
	// 파일경로
	public static final String UPLOAD_PATH_KEY = "__UPLOAD_PATH__";
	public static final String UPLOAD_PATH = "C://project/uploadFile/";
	
	// 파일경로, 원본파일명, 임시파일명
	public static final String FILE_PATH = "__FILE_PATH__";
	public static final String FILE_NAME = "__FILE_NAME__";
	public static final String FILE_TEMP = "__FILE_TEMP__";
	
	// RSA 비대칭키
	public static final String PRIVATE_KEY = "__PRIVATE_KEY__";
	public static final String PUBLIC_KEY = "__PUBLIC_KEY__";
	
	// ERROR 메시지
	public static final String ERROR_MESSAGE = "__ERROR__";
	public static final String ERROR_FILE_UPLOAD = "파일 업로드에 실패하였습니다.";
}
