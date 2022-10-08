package org.zerock.momofit.domain.signIn;

import java.sql.Date;

import lombok.Value;

@Value
public class LoginVO {
	
	private Integer user_no;
	private String id;
	private String nickname;
	private String email;
	private String user_name;
	private Date birth;
	private String tel;
	private String gender;
	private String user_loc;
	private String profile_name;
	private String profile_temp;
	private String profile_path;
	private String profile_info;
	private String sports1;
	private String sports2;
	private String sports3;
	
	/* 
	 * 이미지 파일 확인여부
	 * @param : profile_name
	 * 
	 * return : 빈문자열, 비어있으면 true리턴
	 */
	public boolean isEmptyProfileImg() {
		
		return this.profile_name == null || this.profile_name.isBlank();
		
	} // isProfileImg
	
} // end class
