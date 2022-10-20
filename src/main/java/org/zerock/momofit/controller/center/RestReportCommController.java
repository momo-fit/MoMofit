package org.zerock.momofit.controller.center;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.zerock.momofit.domain.report.comment.reportCommDTO;
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
	public ResponseEntity<List<reportCommVO>> getComm(int report_no) throws ControllerException {
		
		try {
			List<reportCommVO> vo = this.service.getCommList(report_no);
			
			return new ResponseEntity<>(vo, HttpStatus.OK);
		}catch (Exception e) {
			throw new ControllerException(e);
		} // try-catch
		
	} // getComm
	
	@PostMapping("/comm-register")
	public boolean registerComm(reportCommDTO dto) throws ControllerException {
		
		try {
			 return this.service.registerComm(dto);
		}catch (Exception e) {
			throw new ControllerException(e);
		} // try-catch
		
	} // registerComm
	
	@PutMapping(
			value =  "/comm-modify",
			produces = {MediaType.TEXT_PLAIN_VALUE})
	public ResponseEntity<String> modifyComm(@RequestBody reportCommDTO dto) throws ControllerException {
		try {
			boolean modifyStatus = this.service.modifyComm(dto);
			
			if(modifyStatus) {
				return new ResponseEntity<>("success", HttpStatus.OK);
			} else {
				return new ResponseEntity<>("fail", HttpStatus.INTERNAL_SERVER_ERROR);		
			} // if-else
			
		}catch (Exception e) {
			throw new ControllerException(e);
		} // try-catch
	} // modifyComm
	
	@DeleteMapping(value =  "/comm-remove")
	public ResponseEntity<String> removeComm(@RequestBody int report_comm_no) throws ControllerException {
		log.info("report_comm_no : {}", report_comm_no);
		
		try {
			boolean removeStatus = this.service.removeComm(report_comm_no);
			
			if(removeStatus) {
				return new ResponseEntity<>("success", HttpStatus.OK);
			} else {
				return new ResponseEntity<>("fail", HttpStatus.INTERNAL_SERVER_ERROR);		
			} // if-else
			
		}catch (Exception e) {
			throw new ControllerException(e);
		} // try-catch
		
	} // removeComm
	
	
	
}
