package org.zerock.momofit.mapper.picboard;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.zerock.momofit.domain.picboard.Criteria;
import org.zerock.momofit.domain.picboard.PicBoardCommDTO;
import org.zerock.momofit.domain.picboard.PicBoardCommVO;
import org.zerock.momofit.domain.picboard.PicBoardDTO;
import org.zerock.momofit.domain.picboard.PicBoardVO;
import org.zerock.momofit.domain.picboard.board_imgDTO;
import org.zerock.momofit.exception.DAOException;

public interface PicBoardCommMapper {

	
	// 1. 댓글 조회하기
	public abstract List<PicBoardCommVO> read_comm(@Param("board_no")int board_no) throws DAOException;
	
	// 2. 댓글 작성하기
	public abstract Integer writeComm(PicBoardCommDTO dto) throws DAOException;
	
	// 3. 댓글 삭제하기
	public abstract Integer deleteComm(int comm_no) throws DAOException;
	
	// 4. 댓글 수정하기
	public abstract Integer updateComm(PicBoardCommDTO dto) throws DAOException;
}
