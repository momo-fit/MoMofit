package org.zerock.momofit.mapper.reportMapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.zerock.momofit.domain.report.comment.reportCommDTO;
import org.zerock.momofit.domain.report.comment.reportCommVO;
import org.zerock.momofit.exception.DAOException;

public interface reportCommMapper {
	
	// select Comment
	public List<reportCommVO> selectCommView(int report_no) throws DAOException; 
	
	// insert Comment
	public boolean insertComm(reportCommDTO dto) throws DAOException; 
	
	// update Comment
	public boolean updateComm(reportCommDTO dto) throws DAOException; 
	
	// delete Comment
	public boolean deleteComm(int report_comm_no) throws DAOException; 
	
	// update status
	public boolean updateStatus(@Param("res_num") int res_num ,@Param("report_no")  int report_no) throws DAOException; 
	
}
