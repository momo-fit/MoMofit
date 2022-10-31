package org.zerock.momofit.domain.picboard;

import java.util.Date;

import lombok.Data;
import lombok.NoArgsConstructor;
@NoArgsConstructor
@Data
public class PicBoardCommDTO {

	
	private Integer comm_no;
	private Integer board_no;
	private String text;
	private Date comm_date;
	private Date edit;
	private Integer parent_no;
	private Integer user_no;
	
	private String nickname; //유저테이블에 있는 닉네임
}
