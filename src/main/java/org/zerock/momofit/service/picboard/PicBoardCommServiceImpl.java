package org.zerock.momofit.service.picboard;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.zerock.momofit.domain.picboard.PicBoardCommDTO;
import org.zerock.momofit.domain.picboard.PicBoardCommVO;
import org.zerock.momofit.exception.DAOException;
import org.zerock.momofit.exception.ServiceException;
import org.zerock.momofit.mapper.picboard.PicBoardCommMapper;
import org.zerock.momofit.mapper.picboard.PicBoardMapper;

import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;

@Log4j2
@NoArgsConstructor 
@Service
public class PicBoardCommServiceImpl implements PicBoardCommService {
	
	@Setter(onMethod_=@Autowired)
	private PicBoardCommMapper PicBoardCommMapper;
	



	@Override
	public List<PicBoardCommVO> readCommList(@Param("board_no")int board_no) throws ServiceException {
		
		try {
			return PicBoardCommMapper.read_comm(board_no);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
	}//댓글 조회하기 


	@Override
	public boolean registerComm(PicBoardCommDTO dto) throws ServiceException {
	
		try {
			return this.PicBoardCommMapper.writeComm(dto)==1;
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
	}//댓글 작성하기 


	@Override
	public Integer removeComm(int comm_no) throws ServiceException {
		
		try {
			return this.PicBoardCommMapper.deleteComm(comm_no);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
	}//댓글 삭제하기 


	@Override
	public Integer modifyComm(PicBoardCommDTO dto) throws ServiceException {
		
		try {
			return this.PicBoardCommMapper.updateComm(dto);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
	}//댓글 수정하기
	
	
	
	
	
	
	
	

	
	

}
