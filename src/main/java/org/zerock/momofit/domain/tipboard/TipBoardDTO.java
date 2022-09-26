package org.zerock.momofit.domain.tipboard;

import lombok.Data;

@Data
public class TipBoardDTO {
	
	private Integer board_no;
	private String title;
	private String text;
	private Integer board_like;
	private Integer hits;

	private Integer img_check;
	private Integer user_no;
	private Integer category_no;
	private Integer subject_no;
	
} // end class
