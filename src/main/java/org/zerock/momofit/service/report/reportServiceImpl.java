package org.zerock.momofit.service.report;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.zerock.momofit.common.SharedScopeKeys;
import org.zerock.momofit.domain.report.Criteria;
import org.zerock.momofit.domain.report.reportDTO;
import org.zerock.momofit.domain.report.reportFindUsersVO;
import org.zerock.momofit.domain.report.reportImgVO;
import org.zerock.momofit.domain.report.reportListVO;
import org.zerock.momofit.domain.report.reportUpdateDTO;
import org.zerock.momofit.domain.report.reportUpdateVO;
import org.zerock.momofit.domain.report.reportViewVO;
import org.zerock.momofit.exception.ServiceException;
import org.zerock.momofit.mapper.reportMapper.reportMapper;

import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;

@NoArgsConstructor
@Log4j2

@Service
public class reportServiceImpl implements reportService {
	
	@Setter(onMethod_ = {@Autowired})
	private reportMapper mapper;
	
	@Override
	public List<reportListVO> getReportLists(Criteria cri) throws ServiceException {
		log.trace("getReportLists() invoked.");
		
		try {
			return this.mapper.selectListReport(cri);
		} catch (Exception e) {
			throw new ServiceException(e);
		} // try-catch
		
	} // end getReportLists

	@Override
	public Integer getTotalCnt() throws ServiceException {
		log.trace("getTotalCnt() invoked.");
		
		try {
			return this.mapper.getTotalCnt();
		} catch (Exception e) {
			throw new ServiceException(e);
		} // try-catch
		
	} // end getTotalCnt

	@Override
	public reportViewVO getReportView(int reportNum) throws ServiceException {
		log.trace("getReportView() invoked.");
		
		try {
			return this.mapper.selectReportView(reportNum);
		} catch (Exception e) {
			throw new ServiceException(e);
		} // try-catch
	}

	@Override
	public boolean registerReport(reportDTO dto, MultipartFile file) throws ServiceException {
		log.trace("registerReport() invoked.");
		
		try {
			// 파일 저장 저리
			Date date = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
			
			String today = sdf.format(date);
			String targetDir = SharedScopeKeys.UPLOAD_PATH+today;
			
			String imgName = file.getOriginalFilename();
			
			UUID uuid = UUID.randomUUID();
			
			if(imgName != "") {
				
				String profile_temp = uuid.toString();	
				File folder = new File(targetDir);
				
				// 해당폴더 없을시 생성
				if(!folder.isDirectory()) {
					folder.mkdirs();
				} // if

				String targetFile = targetDir +"/"+ profile_temp+ "_" +file.getOriginalFilename();
				log.info("targetFile: {}", targetFile);
				file.transferTo( new File(targetFile));
				
				dto.setTemp(profile_temp);
				dto.setPath(today);
				dto.setImg_check(1);
				log.info("dto: {}", dto);
				return this.mapper.postReportContent(dto);
			} else {
				return this.mapper.postReportContent(dto);
			} // else-if
			
			
		} catch (Exception e) {
			throw new ServiceException(e);
		} // try-catch
	}

	@Override
	public List<reportFindUsersVO> getReportUsersList(String inputNic) throws ServiceException {
		log.trace("getTotalCnt() invoked.");
		
		try {
			return this.mapper.selectReportUsers(inputNic);
		} catch (Exception e) {
			throw new ServiceException(e);
		} // try-catch
	}

	@Override
	public reportImgVO getReportImg(int reportNo) throws ServiceException {		
		log.trace("getReportImg() invoked.");
		
		try {
			return this.mapper.selectReportImg(reportNo);
		} catch (Exception e) {
			throw new ServiceException(e);
		} // try-catch
		
	}

	@Override
	public reportUpdateVO getReportModifyContent(int report_no) throws ServiceException {
		log.trace("getReportRegiserContent() invoked.");
		
		try {
			return this.mapper.selectReportUpdateData(report_no);
		} catch (Exception e) {
			throw new ServiceException(e);
		} // try-catch
		
	} // getReportRegiserContent

	@Override
	public boolean putUpdateReport(reportUpdateDTO dto) throws ServiceException {
		log.trace("putUpdateReport({}) invoked.", dto);
		
		try {
			return this.mapper.updateReport(dto);
		} catch (Exception e) {
			throw new ServiceException(e);
		} // try-catch
		
	} // putUpdateReport

	@Override
	public boolean deleteReport(Map<String, String> map) throws ServiceException {
		log.trace("deleteReport({}) invoked.", map);
			
		
		try {
			String imgPath =  map.get("imgPath");
			String imgTemp = map.get("imgTemp");
			int report_no = Integer.parseInt(map.get("report_no"));
			
			boolean deleteRes = this.mapper.deleteReport(report_no);
			
			if(imgPath != "" && imgTemp != "" && deleteRes) {
				String imgpath = SharedScopeKeys.UPLOAD_PATH + imgPath + "/" + imgTemp;
				
				File file = new File(imgpath);
				
				file.delete();
			} // if
			
			return deleteRes;

		} catch (Exception e) {
			throw new ServiceException(e);
		} // try-catch
	} // deleteReport

} // end class
