package org.zerock.momofit.domain.qnaboard;

import lombok.Data;

@Data
public class qna_imgDTO {


	private Integer qna_img_no;//이미지 파일 번호
	
	private Integer qna_no; //글 번호
	
	private String qna_img_name; //이미지파일 이름
	
	private String temp; //uuid
	
	private String path; //파일 경로 
	
	
}
