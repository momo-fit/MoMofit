package org.zerock.momofit.service.report;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.zerock.momofit.domain.report.comment.reportCommDTO;
import org.zerock.momofit.domain.report.comment.reportCommVO;
import org.zerock.momofit.exception.ServiceException;

public interface reportCommService {
	
	// get comment List
	public List<reportCommVO> getCommList(int report_no) throws ServiceException;
	
	// register comment 
	public boolean registerComm(reportCommDTO dto) throws ServiceException;
	
	// modify comment 
	public boolean modifyComm(reportCommDTO dto) throws ServiceException;
	
	// remove comment 
	public boolean removeComm(int report_comm_no) throws ServiceException;
	
} // end interface
