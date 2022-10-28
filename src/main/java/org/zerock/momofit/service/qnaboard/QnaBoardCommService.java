package org.zerock.momofit.service.qnaboard;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.zerock.momofit.domain.picboard.Criteria;
import org.zerock.momofit.domain.picboard.PicBoardCommDTO;
import org.zerock.momofit.domain.qnaboard.QnaBoardCommDTO;

public interface QnaBoardCommService {


	// 1. 댓글 조회하기
	public QnaBoardCommDTO getComm(Integer qna_comm_no);
	//public abstract List<PicBoardCommVO> readCommList(@Param("board_no")int board_no) throws ServiceException;
	
	// 2. 댓글 작성하기
	public Integer registerComm(QnaBoardCommDTO Commdto); 
	
	// 3. 댓글 삭제하기
	public Integer removeComm(Integer qna_comm_no);
	
	// 4. 댓글 수정하기
	public Integer modifyComm(QnaBoardCommDTO dto);
	
	// 5. 해당 게시글에 있는 전체 댓글 조회
	public abstract List<QnaBoardCommDTO> getCommList(@Param("qna_no")Integer qna_no,@Param("cri") Criteria cri);
 }
