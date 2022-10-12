package org.zerock.momofit.domain.report;

import lombok.Value;

@Value
public class reportUpdateVO {
	
	private Integer report_no;
	private String title;
	private String text;
	private int img_check;
	private String report_user;
	private String report_type;	
	private String report_img_name;
	private String temp;
	private String path;
	
}
