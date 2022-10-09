package org.zerock.momofit.service.freeboard;

import java.util.List;

import org.zerock.momofit.domain.freeboard.Criteria;
import org.zerock.momofit.domain.freeboard.FreeBoardDTO;
import org.zerock.momofit.domain.freeboard.FreeBoardVO;
import org.zerock.momofit.exception.ServiceException;

public interface FreeBoardService {
	
	// 각 웹 3계층의 성격에 맞게 추상메소드의 이름을 결정하라
	
	// 1. 게시글 전체목록 획득 (게시글 번호의 역순으로)
	public abstract List<FreeBoardVO> getList() throws ServiceException;
	
	// 1-1. 페이징 처리된 게시글 목록 획득
	public abstract List<FreeBoardVO> getListPerPage(Criteria cri) throws ServiceException;
	
	// 2. 새로운 게시글 등록
	public abstract boolean register(FreeBoardDTO dto) throws ServiceException;
	
	// 3. 기존 게시글 수정
	public abstract boolean modify(FreeBoardDTO dto) throws ServiceException;
	
	// 4. 기존 게시글 삭제
	public abstract boolean remove(FreeBoardDTO dto) throws ServiceException;
	
	// 5. 기존 게시글 상세조회
	public abstract FreeBoardVO get(FreeBoardDTO dto) throws ServiceException;
	
	// 6. 총 게시물 개수 조회
	public abstract int getTotal() throws ServiceException;

} // end interface
