package org.zerock.momofit.domain.report;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Value;

@Value
public class reportFindUsersVO {
	
	private String nickname;
	private Integer report_no;
	private Integer user_no;
	private Integer report_user;
	private String title;
	private String text;

	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date report_date;
	
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date EDIT;
	
	private Integer img_check;	
	private Integer report_result;
	private String target_nickname;
	
}
