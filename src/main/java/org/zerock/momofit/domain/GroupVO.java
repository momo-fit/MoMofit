package org.zerock.momofit.domain;


import java.util.Date;

import lombok.Data;

@Data
public class GroupVO {
	
	private Integer group_no;
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
	private Integer user_no;
	
}