package org.zerock.momofit.domain.main;

import java.sql.Date;

import lombok.Data;
import lombok.Value;

@Value
public class BoardVO {
	
	private Integer board_no;
	private Integer category_no;
	private String title;
	private Date board_date;
	private int comm_cnt;
	
} // end class