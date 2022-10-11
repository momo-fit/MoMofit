package org.zerock.momofit.service.picboard;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.zerock.momofit.domain.picboard.Criteria;
import org.zerock.momofit.domain.picboard.PicBoardCommDTO;
import org.zerock.momofit.domain.picboard.PicBoardCommVO;
import org.zerock.momofit.domain.picboard.PicBoardDTO;
import org.zerock.momofit.domain.picboard.PicBoardVO;
import org.zerock.momofit.domain.picboard.board_imgDTO;
import org.zerock.momofit.exception.DAOException;
import org.zerock.momofit.exception.ServiceException;

public interface PicBoardCommService {


	// 1. 댓글 조회하기
	public abstract List<PicBoardCommVO> readCommList(@Param("board_no")int board_no) throws ServiceException;
	
	// 2. 댓글 작성하기
	public abstract boolean registerComm(PicBoardCommDTO dto) throws ServiceException;
	
	// 3. 댓글 삭제하기
	public abstract Integer removeComm(int comm_no) throws ServiceException;
	
	// 4. 댓글 수정하기
	public abstract Integer modifyComm(PicBoardCommDTO dto) throws ServiceException;
	
	
 }
