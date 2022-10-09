package org.zerock.momofit.domain.freeboard;

import java.sql.Date;

import lombok.Value;

@Value
public class FreeBoardVO {
	
	private Integer board_no;
	private String title;
	private String text;
	private Integer board_like;
	private Integer hits;
	private Date board_date;
	private Date edit;
	private Integer img_check;
	private Integer user_no;
	private Integer category_no;
	private Integer subject_no;
	
} // end class
