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
	public PicBoardCommDTO getComm(Integer comm_no);
	//public abstract List<PicBoardCommVO> readCommList(@Param("board_no")int board_no) throws ServiceException;
	
	// 2. 댓글 작성하기
	public Integer registerComm(PicBoardCommDTO Commdto); 
	
	// 3. 댓글 삭제하기
	public Integer removeComm(Integer comm_no);
	
	// 4. 댓글 수정하기
	public Integer modifyComm(PicBoardCommDTO dto);
	
	// 5. 해당 게시글에 있는 전체 댓글 조회
	public abstract List<PicBoardCommDTO> getCommList(@Param("board_no")Integer board_no,@Param("cri") Criteria cri);
 }
