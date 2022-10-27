package org.zerock.momofit.mapper.qnaboard;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.zerock.momofit.domain.picboard.Criteria;
import org.zerock.momofit.domain.picboard.PicBoardCommDTO;
import org.zerock.momofit.domain.qnaboard.QnaBoardCommDTO;

public interface QnaBoardCommMapper {

	
	// 1. 댓글 조회하기
	public QnaBoardCommDTO readComm(Integer qna_no); 
	
	// 2. 댓글 작성하기
	public Integer insertComm(QnaBoardCommDTO Commdto);
	
	// 3. 댓글 삭제하기
	public Integer deleteComm(Integer qna_comm_no);
	
	// 4. 댓글 수정하기
	public Integer updateComm(QnaBoardCommDTO Commdto);
	
	// 5. 해당 게시글에 있는 전체 댓글 조회
	public List<QnaBoardCommDTO> readCommList(@Param("qna_no")Integer qna_no,@Param("cri") Criteria cri);
}
