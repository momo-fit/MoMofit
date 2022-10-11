package org.zerock.momofit.mapper.qnaboard;

import java.util.List;

import org.zerock.momofit.domain.qnaboard.Criteria;
import org.zerock.momofit.domain.qnaboard.QnaBoardDTO;
import org.zerock.momofit.domain.qnaboard.QnaBoardVO;
import org.zerock.momofit.exception.DAOException;

public interface QnaBoardMapper {
	
//	// 1.페이징처리가 적용된 게시글 목록 조회하기
//	public abstract List<QnaVO> selectListWithPaging() throws DAOException;
	
	//1-1 게시판 목록 조회하기(페이징처리)
	public abstract List<QnaBoardVO> SelectList(Criteria cri) throws DAOException;
	
	//게시글 작성하기 
	public abstract Integer insert(QnaBoardDTO dto) throws DAOException;
}
