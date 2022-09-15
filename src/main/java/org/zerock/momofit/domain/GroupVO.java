package org.zerock.momofit.domain;


import java.util.Date;

import lombok.Data;

@Data
public class GroupVO {
	
	private Integer groupNo;
	private String groupName;
	private String sports;
	private String groupLoc;
	private Integer memberMax;
	private Date schedule;
	private Date groupDate;
	private Date edit;
	private String info;
	private Integer groupLike;
	private String hashtag1;
	private String hashtag2;
	private String hashtag3;
	private Integer userNo;
	
}
