package org.zerock.momofit.service.report;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;
import org.zerock.momofit.domain.report.Criteria;
import org.zerock.momofit.domain.report.reportDTO;
import org.zerock.momofit.domain.report.reportListVO;
import org.zerock.momofit.domain.report.reportViewVO;
import org.zerock.momofit.exception.ServiceException;

public interface reportService {
	
	public abstract List<reportListVO> getReportLists(Criteria cri) throws ServiceException;
	
	public abstract Integer getTotalCnt() throws ServiceException;
	
	public abstract reportViewVO getReportView(int reportNum) throws ServiceException;
	
	public abstract boolean registerReport(reportDTO dto, MultipartFile file) throws ServiceException;
}
