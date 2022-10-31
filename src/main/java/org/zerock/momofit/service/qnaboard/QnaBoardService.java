package org.zerock.momofit.service.qnaboard;

import java.util.List;

import org.zerock.momofit.domain.picboard.PicBoardVO;
import org.zerock.momofit.domain.qnaboard.Criteria;
import org.zerock.momofit.domain.qnaboard.QnaBoardDTO;
import org.zerock.momofit.domain.qnaboard.QnaBoardVO;
import org.zerock.momofit.domain.qnaboard.qna_imgDTO;
import org.zerock.momofit.exception.ServiceException;

public interface QnaBoardService {
	
	//게시글 목록 조회하기(페이징처리)
	public abstract List<QnaBoardVO> getList(Criteria cri) throws ServiceException;
	
	//새로운 게시글 등록하기
	public abstract boolean register(QnaBoardDTO dto) throws ServiceException;

	//2.새로운 게시글 등록 + 게시판 등록시 파일첨부도 함께 되게하기  
	//public abstract boolean register(QnaBoardDTO dto) throws ServiceException;
	
	//3.기존 게시글 수정
	public abstract boolean modify(QnaBoardDTO dto) throws ServiceException;
	
	//4.기존 게시글 상세조회 + ***조회수 
	public abstract QnaBoardVO get(QnaBoardDTO dto) throws ServiceException;
	
	//5. 기존 게시글 삭제하기
	public abstract boolean remove(QnaBoardDTO dto)throws ServiceException;
	
	//6. 총 게시글 갯수 조회
	public abstract int getTotal(Criteria cri) throws ServiceException;
	
	//7. 해당 게시물의 업로드 된 파일 조회하기 
	public abstract List<qna_imgDTO> imageList(int qna_no) throws ServiceException;

}
