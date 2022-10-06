package org.zerock.momofit.domain.freeboard;

import lombok.Data;

@Data
public class AttachImageVO {
	
	private String fileName;   // 파일명
	private String uploadPath; // 파일경로
	private String uuid;
	
	private Integer board_no;  // 글번호
	
} // end class
