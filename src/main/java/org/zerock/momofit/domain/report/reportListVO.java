package org.zerock.momofit.domain.report;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Value;

@Value
public class reportListVO {
	
	private Integer report_no;
	private String title;
	private Integer report_result;

	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date report_date;
	
	private String nickname;

}
