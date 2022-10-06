package org.zerock.momofit.domain.chat;

import java.util.Date;

import lombok.Data;

@Data
public class GroupMemberVO {
 
	private Integer group_no;
	private Integer group_member_no;
	private Integer group_user_no;
	private Date join_date;
	
	private String nickname;
	
} // end class
