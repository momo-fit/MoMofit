package org.zerock.momofit.domain.picboard;

import lombok.Data;

@Data
public class board_imgDTO {


	private Integer img_no;//이미지 파일 번호
	
	private Integer board_no; //글 번호
	
	private String board_img_name; //이미지파일 이름
	
	private String temp; //uuid
	
	private String path; //파일 경로 
	
	
}
