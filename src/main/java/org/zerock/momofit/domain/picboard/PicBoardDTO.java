package org.zerock.momofit.domain.picboard;

import java.util.Date;
import java.util.List;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
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
	
	//말머리(중고거래만쓰긴함)
	private Integer subject_no;
	
	// --------- board_img 테이블 데이터 --------
	private Integer img_no;//이미지 파일 번호
	
	private Integer boardNo;	//게시판 번호 (얘는 이미지 테이블에 있는데 argument type mismatch 방지용으로 선언만)

	private String board_img_name; //이미지파일 이름
	
	private String temp; //uuid
	
	private String path; //파일 경로 
	
	// --------- board_img 테이블 데이터 --------
	
	//파일업로드관련 
	private List<board_imgDTO> imageList;
	

}
