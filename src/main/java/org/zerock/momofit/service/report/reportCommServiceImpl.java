package org.zerock.momofit.service.report;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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


} // end class
