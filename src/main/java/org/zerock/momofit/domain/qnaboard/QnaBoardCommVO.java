package org.zerock.momofit.domain.qnaboard;

import java.util.Date;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class QnaBoardCommVO {

	
	private Integer qna_comm_no;
	private Integer qna_no;
	private String text;
	private Date qna_comm_date;
	private Date edit;
	private String admin_id;
	private Integer user_no;
	
	private String nickname; //유저테이블에 있는 닉네임
}
