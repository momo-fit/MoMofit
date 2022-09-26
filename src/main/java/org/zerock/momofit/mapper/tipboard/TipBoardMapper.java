package org.zerock.momofit.mapper.tipboard;

import java.util.List;

import org.zerock.momofit.domain.tipboard.Criteria;
import org.zerock.momofit.domain.tipboard.TipBoardDTO;
import org.zerock.momofit.domain.tipboard.TipBoardVO;
import org.zerock.momofit.exception.DAOException;

public interface TipBoardMapper {
	
	// 1. 게시판 테이블의 전체 목록 조회하기 - Mapper XML 파일로 처리
	public abstract List<TipBoardVO> selectAllList() throws DAOException;
	
	// 1-1. 새로이 페이징 처리가 가능한, 게시물 목록 조회하기
	public abstract List<TipBoardVO> selectListWithPaging(Criteria cri) throws DAOException;
		
	// 2. 새로운 게시글을 등록하기 (CREATE) - Mapper XML 파일로 처리
	public abstract Integer insert(TipBoardDTO dto) throws DAOException;
	public abstract Integer insertSelectKey(TipBoardDTO dto) throws DAOException; // *** : 새로이 입력된 게시글의 bno을 반환받기를 원하는 경우

	// 3. 기존 게시글 상세조회 하기 (READ)  - Mapper XML 파일로 처리
	public abstract TipBoardVO select(TipBoardDTO dto) throws DAOException; 
		
	// 4. 기존 게시글 수정하기 (UPDATE) - Mapper XML 파일로 처리
	public abstract Integer update(TipBoardDTO dto) throws DAOException;
	
	// 5. 기존 게시글 삭제하기 (DELETE) - Mapper XML 파일로 처리
	public abstract Integer delete(Integer dto) throws DAOException;
	
	// 6. 총 게시물의 갯수를 반환
	public abstract Integer getTotalCount() throws DAOException;

} // end interface
