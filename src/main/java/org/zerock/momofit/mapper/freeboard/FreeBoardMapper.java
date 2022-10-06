package org.zerock.momofit.mapper.freeboard;

import java.util.List;

import org.zerock.momofit.domain.freeboard.Criteria;
import org.zerock.momofit.domain.freeboard.FreeBoardDTO;
import org.zerock.momofit.domain.freeboard.FreeBoardVO;
import org.zerock.momofit.exception.DAOException;

public interface FreeBoardMapper {
	
	// 1. 게시판 테이블의 전체 목록 조회하기 - Mapper XML 파일로 처리
	public abstract List<FreeBoardVO> selectAllList() throws DAOException;
	
	// 1-1. 새로이 페이징 처리가 가능한, 게시물 목록 조회하기
	public abstract List<FreeBoardVO> selectListWithPaging(Criteria cri) throws DAOException;
		
	// 2. 새로운 게시글을 등록하기 (CREATE) - Mapper XML 파일로 처리
	public abstract Integer insert(FreeBoardDTO dto) throws DAOException;
	public abstract Integer insertSelectKey(FreeBoardDTO dto) throws DAOException; // *** : 새로이 입력된 게시글의 bno을 반환받기를 원하는 경우

	// 3. 기존 게시글 상세조회 하기 (READ)  - Mapper XML 파일로 처리
	public abstract FreeBoardVO select(FreeBoardDTO dto) throws DAOException; 
		
	// 4. 기존 게시글 수정하기 (UPDATE) - Mapper XML 파일로 처리
	public abstract Integer update(FreeBoardDTO dto) throws DAOException;
	
	// 5. 기존 게시글 삭제하기 (DELETE) - Mapper XML 파일로 처리
	public abstract Integer delete(Integer dto) throws DAOException;
	
	// 6. 총 게시물의 갯수를 반환
	public abstract Integer getTotalCount() throws DAOException;

} // end interface
