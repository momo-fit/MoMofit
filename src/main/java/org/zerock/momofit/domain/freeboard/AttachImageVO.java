package org.zerock.momofit.domain.freeboard;

import lombok.Data;

@Data
public class AttachImageVO {
	
	private String fileName;   // 파일명
	private String uploadPath; // 파일경로
	private String uuid;
	
	private Integer img_no;    // 이미지번호 (PK)
	private Integer board_no;  // 글번호
	private String name;	   // 원본파일명
	private String temp;       // 임시파일명
	private String path;       // 파일경로
	
} // end class
