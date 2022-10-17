package org.zerock.momofit.service.report;

import java.util.List;

import org.zerock.momofit.domain.report.comment.reportCommVO;
import org.zerock.momofit.exception.ServiceException;

public interface reportCommService {
	
	// get comment List
	public List<reportCommVO> getCommList(int report_no) throws ServiceException;
	
	
	
} // end interface
