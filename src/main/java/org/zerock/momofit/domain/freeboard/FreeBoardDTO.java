package org.zerock.momofit.domain.freeboard;

import lombok.Data;

@Data
public class FreeBoardDTO {
	
	private Integer board_no;
	private String title;
	private String text;
	private Integer board_like;
	private Integer hits;

	private Integer img_check;
	private Integer user_no;
	private Integer category_no;
	private Integer subject_no;
	
	private String nickname;

	
	// 이미지 첨부
//	private String uploadPath; // 경로
//	private String uuid;
//	private String fileName;   // 파일명
	
} // end class
