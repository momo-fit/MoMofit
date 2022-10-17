package org.zerock.momofit.mapper.reportMapper;

import java.util.List;

import org.zerock.momofit.domain.report.comment.reportCommVO;
import org.zerock.momofit.exception.DAOException;

public interface reportCommMapper {
	
	// select Comment
	public List<reportCommVO> selectCommView(int report_no) throws DAOException; 
	
}
