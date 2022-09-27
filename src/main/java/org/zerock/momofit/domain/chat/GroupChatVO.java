package org.zerock.momofit.domain.chat;

import java.util.Date;
import java.util.List;

import lombok.Data;


@Data
public class GroupChatVO {
	
	private Integer group_no;
	private String group_name;
	private String sports;
	private String group_loc;
	private Integer member_max;
	private Date schedule;
	private String info;
	private Integer user_no;
	
	private String group_img;
	private String path;
	private String temp;
	
	private List<GroupMemberVO> list;

} // end class
