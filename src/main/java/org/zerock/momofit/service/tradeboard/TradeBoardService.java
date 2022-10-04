package org.zerock.momofit.service.tradeboard;

import java.util.List;

import org.zerock.momofit.domain.tradeboard.Criteria;
import org.zerock.momofit.domain.tradeboard.TradeBoardDTO;
import org.zerock.momofit.domain.tradeboard.TradeBoardVO;
import org.zerock.momofit.exception.ServiceException;

public interface TradeBoardService {
	
	
	// 1. 게시글 전체목록 획득(게시글번호의 역순으로...)
	public abstract List<TradeBoardVO> getList() throws ServiceException;
	
	// 1-1. 페이징 처리된 게시글 목록 획득
	public abstract List<TradeBoardVO> getListPerPage(Criteria cri) throws ServiceException;
	
	// 2. 새로운 게시글 등록
	public abstract boolean register(TradeBoardDTO dto) throws ServiceException;
	
	// 3. 기존 게시글 수정
	public abstract boolean modify(TradeBoardDTO dto) throws ServiceException;
	
	// 4. 기존 게시글 삭제
	public abstract boolean remove(TradeBoardDTO dto) throws ServiceException;
	
	// 5. 특정 게시글 상세조회
	public abstract TradeBoardVO get(TradeBoardDTO dto) throws ServiceException;
	
	// 6. 총 게시물 개수 조회
	public abstract int getTotal() throws ServiceException;

	
} // end interface
