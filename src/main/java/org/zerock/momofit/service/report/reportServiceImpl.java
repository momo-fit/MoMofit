package org.zerock.momofit.service.report;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.zerock.momofit.common.SharedScopeKeysCommon;
import org.zerock.momofit.domain.report.Criteria;
import org.zerock.momofit.domain.report.reportDTO;
import org.zerock.momofit.domain.report.reportFindUsersVO;
import org.zerock.momofit.domain.report.reportListVO;
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
		log.trace("getTotalCnt() invoked.");
		
		try {
			return this.mapper.selectReportView(reportNum);
		} catch (Exception e) {
			throw new ServiceException(e);
		} // try-catch
	}

	@Override
	public boolean registerReport(reportDTO dto, MultipartFile file) throws ServiceException {
		log.trace("getTotalCnt() invoked.");
		
		try {
			// 파일 저장 저리
			Date date = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
			
			String today = sdf.format(date);
			String targetDir = SharedScopeKeysCommon.UPLOAD_PATH+today;
			
			String imgName = file.getOriginalFilename();
			
			UUID uuid = UUID.randomUUID();
			
			if(imgName != "") {
				
				String profile_temp = uuid + "_" +file.getOriginalFilename();	
				File folder = new File(targetDir);
				
				// 해당폴더 없을시 생성
				if(!folder.isDirectory()) {
					folder.mkdirs();
				} // if

				String targetFile = targetDir +"/"+ profile_temp;
				log.info("targetFile: {}", targetFile);
				file.transferTo( new File(targetFile));
				
				dto.setTemp(profile_temp);
				dto.setPath(targetDir);
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
	} // getReportUsersList

}
