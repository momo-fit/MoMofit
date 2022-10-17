package org.zerock.momofit.mapper.picboard;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.zerock.momofit.domain.picboard.Criteria;
import org.zerock.momofit.domain.picboard.PicBoardCommDTO;
import org.zerock.momofit.domain.picboard.PicBoardCommVO;
import org.zerock.momofit.exception.DAOException;

public interface PicBoardCommMapper {

	
	// 1. 댓글 조회하기
	public PicBoardCommDTO readComm(Integer board_no); 
	//public abstract PicBoardCommVO readComm(PicBoardCommDTO dto)throws DAOException;//매개변수..board_no?
	//public abstract List<PicBoardCommVO> read_comm(@Param("board_no")int board_no) throws DAOException;
	
	// 2. 댓글 작성하기
	public Integer insertComm(PicBoardCommDTO Commdto);
	
	// 3. 댓글 삭제하기
	public Integer deleteComm(Integer comm_no);
	
	// 4. 댓글 수정하기
	public Integer updateComm(PicBoardCommDTO Commdto);
	
	// 5. 해당 게시글에 있는 전체 댓글 조회
	public List<PicBoardCommDTO> readCommList(@Param("board_no")Integer board_no,@Param("cri") Criteria cri);
}
