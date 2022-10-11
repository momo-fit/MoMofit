package org.zerock.momofit.domain.qnaboard;

import java.util.Date;

import lombok.Data;

@Data

public class QnaBoardDTO {

	private Integer qna_no;
	private String title;
	private String text;
	private Integer img_check;
	private Integer qna_result;
	private Integer user_no;
}
