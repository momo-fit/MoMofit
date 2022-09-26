package org.zerock.momofit.domain.picboard;

import java.util.Date;

import lombok.Value;
@Value
public class PicBoardVO {
	
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
	
	//작성일
	private Date board_date;
	
	//수정일
	private Date edit;
	
	//이미지첨부여부
	private Integer img_check;
	
	//회원번호
	private Integer user_no;
	
	//카테고리
	private Integer category_no;
	

	
	
}
