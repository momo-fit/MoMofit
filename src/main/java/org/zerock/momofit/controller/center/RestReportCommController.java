package org.zerock.momofit.controller.center;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.zerock.momofit.domain.report.comment.reportCommVO;
import org.zerock.momofit.exception.ControllerException;
import org.zerock.momofit.service.report.reportCommService;

import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;

@NoArgsConstructor
@Log4j2

@RestController
@RequestMapping("/report/comm")
public class RestReportCommController {
	
	@Setter(onMethod_ = {@Autowired})
	private reportCommService service;
	
	@GetMapping("/comm-list")
	public List<reportCommVO> getComm(int report_no) throws ControllerException {
		
		try {

			return this.service.getCommList(report_no);
			
		}catch (Exception e) {
			throw new ControllerException(e);
		} // try-catch
		
	}
	
	
	
}
