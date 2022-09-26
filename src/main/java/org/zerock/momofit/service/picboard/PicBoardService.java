package org.zerock.momofit.service.picboard;

import java.util.List;

import org.zerock.momofit.domain.picboard.Criteria;
import org.zerock.momofit.domain.picboard.PicBoardDTO;
import org.zerock.momofit.domain.picboard.PicBoardVO;
import org.zerock.momofit.exception.ServiceException;

public interface PicBoardService {
	
	//1.게시글 전체 목록 획득 (게시글 번호의 역순으로)
	public abstract List <PicBoardVO> getList() throws ServiceException;
	
	//1-1 페이징처리가 적용된 게시글 목록 조회하기 
	public abstract List<PicBoardVO> getListWithPaging(Criteria cri) throws ServiceException;
	
	//2.새로운 게시글 등록
	public abstract boolean register(PicBoardDTO dto) throws ServiceException;
	
	//3.기존 게시글 수정
	public abstract boolean modify(PicBoardDTO dto) throws ServiceException;
	
	//4.기존 게시글 상세조회
	public abstract PicBoardVO get(PicBoardDTO dto) throws ServiceException;
	
	//5. 기존 게시글 삭제하기
	public abstract boolean remove(PicBoardDTO dto)throws ServiceException;
	
	//6. 총 게시글 갯수 조회
	public abstract int getTotal(Criteria cri) throws ServiceException;
	
	//7. 게시글 조회수 올리기
//	public abstract int plusHits(PicBoardDTO dto) throws ServiceException;
//	public abstract PicBoardVO plusHits(PicBoardDTO dto)throws ServiceException;
	
//	public abstract void plusHits(PicBoardDTO dto) throws ServiceException;
	
 }
