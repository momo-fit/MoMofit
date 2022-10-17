package org.zerock.momofit.domain.report;

import java.util.Date;

import lombok.Data;

@Data
public class reportDTO {
	
	private Integer report_no;
	private String title;
	private String text;
	private Date report_date;
	private Date edit;
	private Integer img_check;
	private Integer report_result;
	private String report_user;
	private Integer user_no;
	private String admin_id;
	private String report_type;
	
	/* 파일 업로드 */
	private Integer report_img_no;
	private String report_img_name;
	private String temp;
	private String path;
	
}
