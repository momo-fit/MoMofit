package org.zerock.momofit.mapper.reportMapper;

import java.util.List;

import org.zerock.momofit.domain.report.Criteria;
import org.zerock.momofit.domain.report.reportDTO;
import org.zerock.momofit.domain.report.reportListVO;
import org.zerock.momofit.domain.report.reportViewVO;
import org.zerock.momofit.exception.DAOException;

public interface reportMapper {
	
	// 총 길이
	public abstract int getTotalCnt() throws DAOException; 
	
	// 목록
	public abstract List<reportListVO> selectListReport(Criteria cri) throws DAOException;
	
	// 뷰
	public abstract reportViewVO selectReportView(int reportNum) throws DAOException;
	
	// 등록
	public abstract boolean postReportContent(reportDTO dto) throws DAOException;

	
}
