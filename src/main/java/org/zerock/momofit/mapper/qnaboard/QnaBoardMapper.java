package org.zerock.momofit.mapper.qnaboard;

import java.util.List;

import org.zerock.momofit.domain.picboard.PicBoardVO;
import org.zerock.momofit.domain.picboard.board_imgDTO;
import org.zerock.momofit.domain.qnaboard.Criteria;
import org.zerock.momofit.domain.qnaboard.QnaBoardDTO;
import org.zerock.momofit.domain.qnaboard.QnaBoardVO;
import org.zerock.momofit.domain.qnaboard.qna_imgDTO;
import org.zerock.momofit.exception.DAOException;

public interface QnaBoardMapper {
	
//	// 1.페이징처리가 적용된 게시글 목록 조회하기
//	public abstract List<QnaVO> selectListWithPaging() throws DAOException;
	
	//1. 게시판 목록 조회하기(페이징처리)
	public abstract List<QnaBoardVO> SelectList(Criteria cri) throws DAOException;
	
	//2.게시글 작성하기 
	public abstract Integer insert(QnaBoardDTO dto) throws DAOException;
	
	// 3. 기존 게시글 상세 조회하기
	public abstract QnaBoardVO select(QnaBoardDTO dto)throws DAOException;
	
	// 4. 기존 게시글 수정하기
	public abstract Integer update(QnaBoardDTO dto)throws DAOException;
	
	// 5. 기존 게시글 삭제하기
	public abstract Integer delete(Integer QNA_NO) throws DAOException;
	
	// 6. 총 게시물의 갯수를 반환
	public abstract Integer getTotalCount(Criteria cri) throws DAOException;//검색기능구현할때 페이지를 알아야해서 매개변수에 cri추가해줌
	
	// 7. 게시글 조회수 올리기 
	public abstract Integer plusHits(QnaBoardDTO dto)throws DAOException;
	
	// 8. 새로운 게시글 등록시 첨부파일 이미지 함께 등록
	public abstract Integer insert_img(qna_imgDTO dto) throws DAOException;
	
	// 9. 해당 게시물의 업로드 된 파일 조회하기 
	public abstract List<qna_imgDTO> imageList(int qna_no) throws DAOException;
}
