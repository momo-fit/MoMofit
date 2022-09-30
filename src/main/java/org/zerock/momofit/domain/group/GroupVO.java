package org.zerock.momofit.domain.group;

import java.util.Date;

import lombok.Value;

@Value
public class GroupVO {
	
	private Integer group_no;	// PK - 모임번호
	private String group_name;
	private String sports;
	private String group_loc;
	private Integer member_max;
	private Date schedule;
	private Date group_date;
	private Date edit;
	private String info;
	private Integer group_like;
	private String hashtag1;
	private String hashtag2;
	private String hashtag3;	
	private Integer user_no;	// FK - users 테이블
	private String gender;
	// 이미지
	private String group_img;
	private String temp;
	private String path;
	// 멤버수
	private Integer member_count;
	
<<<<<<< Updated upstream
}
=======
}
>>>>>>> Stashed changes
