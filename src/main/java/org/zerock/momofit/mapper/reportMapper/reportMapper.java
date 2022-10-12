package org.zerock.momofit.mapper.reportMapper;

import java.util.List;

import org.zerock.momofit.domain.report.Criteria;
import org.zerock.momofit.domain.report.reportDTO;
import org.zerock.momofit.domain.report.reportFindUsersVO;
import org.zerock.momofit.domain.report.reportImgVO;
import org.zerock.momofit.domain.report.reportListVO;
import org.zerock.momofit.domain.report.reportUpdateDTO;
import org.zerock.momofit.domain.report.reportUpdateVO;
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

	// 유저 검색
	public abstract List<reportFindUsersVO> selectReportUsers(String inputNic) throws DAOException;
	
	// 이미지 검색
	public abstract reportImgVO selectReportImg(int reportNo) throws DAOException;
	
	// 수정 화면 데이터 검색
	public abstract reportUpdateVO selectReportUpdateData(int report_no) throws DAOException;
	
	// 업데이트
	public abstract boolean updateReport(reportUpdateDTO dto) throws DAOException;
	
	// 삭제
	public abstract boolean deleteReport(int report_no) throws DAOException;
	
}
