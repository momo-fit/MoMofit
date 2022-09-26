package org.zerock.momofit.domain.picboard;

import lombok.Data;

@Data
public class PicBoardDTO {
	
	//게시판 번호
	private Integer board_no;
	
	//게시판 제목
	private String title;
	
	//게시판 내용
	private String text;
	
	//좋아요
	private Integer board_like;
	
	//조회수
	private Integer hits;
	
	
	//이미지첨부여부
	private Integer img_check;
	
	//회원번호
	private Integer user_no;
	

	//카테고리
	private Integer category_no;
}
