package org.zerock.momofit.service.report;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.zerock.momofit.domain.report.comment.reportCommDTO;
import org.zerock.momofit.domain.report.comment.reportCommVO;
import org.zerock.momofit.exception.ServiceException;
import org.zerock.momofit.mapper.reportMapper.reportCommMapper;

import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;

@NoArgsConstructor
@Log4j2

@Service
public class reportCommServiceImpl implements reportCommService {
	
	@Setter(onMethod_ = {@Autowired})
	private reportCommMapper mapper;

	@Override
	public List<reportCommVO> getCommList(int report_no) throws ServiceException {
		log.trace("getCommList() invoked.");
		
		try {
			return this.mapper.selectCommView(report_no);
		} catch (Exception e) {
			throw new ServiceException(e);
		} // try-catch
	
	} // getCommList

	@Override
	public boolean registerComm(reportCommDTO dto) throws ServiceException {
		log.trace("registerComm() invoked.");
		
		try {
			boolean insertRes = this.mapper.insertComm(dto);
			
			if(insertRes) {
				int report_no = dto.getReport_no();
				int res_num = 1;
				
				return this.mapper.updateStatus(res_num, report_no);
			} else {
				return insertRes;
			} // else-if
			
		} catch (Exception e) {
			throw new ServiceException(e);
		} // try-catch
		
	} // registerComm

	@Override
	public boolean modifyComm(reportCommDTO dto) throws ServiceException {
		log.trace("modifyComm() invoked.");
		
		try {
			return this.mapper.updateComm(dto);
		} catch (Exception e) {
			throw new ServiceException(e);
		} // try-catch
		
	} // modifyComm

	@Override
	public boolean removeComm(int report_comm_no) throws ServiceException {
		log.trace("modifyComm() invoked.");
		
		try {
			return this.mapper.deleteComm(report_comm_no);
		} catch (Exception e) {
			throw new ServiceException(e);
		} // try-catch
		
	} // removeComm

} // end class
