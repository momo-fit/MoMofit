package org.zerock.momofit.domain.group;


import java.util.Date;

import lombok.Data;

@Data
public class GroupDTO {
	
	private Integer group_no;	// PK
	private String group_name;
	private String sports;
	private String gender;
	private String group_loc;
	private Integer member_max;
	
//	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")	
	private Date schedule;
	
	private Integer user_no;	// 유저번호 FK
	private Date group_date;
	private Date edit;
	private String info;
	private String hashtag1;
	private String hashtag2;
	private String hashtag3;
	private Integer group_like;
	// 이미지
	private String group_img;	// 이미지 이름
	private String temp;		// uuid
	private String path;		// 경로
	// 멤버수
	private Integer member_count;

	
}
