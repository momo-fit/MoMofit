package org.zerock.momofit.domain.signIn;

import lombok.Data;

@Data
public class LoginDTO {
	
	private String id;
	private String pass;
	
	private Boolean rememberMe;
} // end class
