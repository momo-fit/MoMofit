package org.zerock.momofit.domain.report.comment;

import lombok.Data;

@Data
public class reportCommDTO {
	
	private int report_no;
	private Integer report_comm_no;
	private String text;	
	private String adminId;


}
