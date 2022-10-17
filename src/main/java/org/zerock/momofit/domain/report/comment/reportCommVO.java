package org.zerock.momofit.domain.report.comment;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Value;

@Value
public class reportCommVO {
	
	private Integer report_no;
	private String text;

	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date report_comm_date;


}
