package org.zerock.momofit.domain.qnaboard;

import java.util.Date;

import lombok.Value;

@Value

public class QnaBoardVO {

	private Integer qna_no;
	private String title;
	private String text;
	private Date qna_date;
	private Date edit;
	private Integer img_check;
	private Integer qna_result;
	private Integer user_no;
	
	private String nickname; //유저테이블에 있는 닉네임
}
