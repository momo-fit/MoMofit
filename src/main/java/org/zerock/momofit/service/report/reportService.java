package org.zerock.momofit.service.report;

import java.util.List;
import java.util.Map;

import org.springframework.web.multipart.MultipartFile;
import org.zerock.momofit.domain.report.Criteria;
import org.zerock.momofit.domain.report.reportDTO;
import org.zerock.momofit.domain.report.reportFindUsersVO;
import org.zerock.momofit.domain.report.reportImgVO;
import org.zerock.momofit.domain.report.reportListVO;
import org.zerock.momofit.domain.report.reportUpdateDTO;
import org.zerock.momofit.domain.report.reportUpdateVO;
import org.zerock.momofit.domain.report.reportViewVO;
import org.zerock.momofit.exception.ServiceException;

public interface reportService {
	
	// 리스트 불러오기
	public abstract List<reportListVO> getReportLists(Criteria cri) throws ServiceException;
	
	// 글의 수 구하기
	public abstract Integer getTotalCnt() throws ServiceException;
	
	// 뷰 불러오기
	public abstract reportViewVO getReportView(int reportNum) throws ServiceException;
	
	// 등록
	public abstract boolean registerReport(reportDTO dto, MultipartFile file) throws ServiceException;
	
	// 유저 검색
	public abstract List<reportFindUsersVO> getReportUsersList(String inputNic) throws ServiceException;
	
	// 이미지 로딩
	public abstract reportImgVO getReportImg(int reportNo) throws ServiceException;
	
	// 수정 화면 글 불러오기
	public abstract reportUpdateVO getReportModifyContent(int report_no) throws ServiceException;
	
	// 수정
	public abstract boolean putUpdateReport (reportUpdateDTO dto) throws ServiceException;

	// 삭제
	public abstract boolean deleteReport(Map<String, String>map) throws ServiceException;
}
