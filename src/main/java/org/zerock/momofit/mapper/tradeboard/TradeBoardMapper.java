package org.zerock.momofit.mapper.tradeboard;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.zerock.momofit.domain.tradeboard.Criteria;
import org.zerock.momofit.domain.tradeboard.TradeBoardDTO;
import org.zerock.momofit.domain.tradeboard.TradeBoardVO;
import org.zerock.momofit.exception.DAOException;

public interface TradeBoardMapper {
	
		// 1. 게시판 테이블의 전체목록 조회하기
		@Select("SELECT /*+ index_desc(board) */ * FROM board WHERE category_no =4")
		public abstract List<TradeBoardVO> selectAllList() throws DAOException;
		
		// 1-1. 새로이 페이징 처리가 적용된 게시물 목록 조회 하기
		public abstract List<TradeBoardVO> selectListWithPaging(Criteria cri) throws DAOException;
		
		// 2. 새로운 게시글을 등록하기(CREATE) - Mapper XML 파일로 처리
		public abstract Integer insert(TradeBoardDTO dto) throws DAOException;
		public abstract Integer insertSelectKey(TradeBoardDTO dto) throws DAOException;		// *** : 새로이 입력된 게시글의 board_no를 반환받기를 원하는 경우
		
		// 3. 기존 게시글 상세조회 하기(READ) - Mapper XML 파일로 처리
		public abstract TradeBoardVO select(TradeBoardDTO dto) throws DAOException;
		
		// 4. 기존 게시글 수정하기(UPDATE) - Mapper XML 파일로 처리
		public abstract Integer update(TradeBoardDTO dto) throws DAOException;
		
		// 5. 기존 게시글 삭제하기(DELETE)
		@Delete("DELETE FROM board WHERE board_no = #{board_no}")
		public abstract Integer delete(@Param("board_no") Integer board_no) throws DAOException;
		
		// 6. 총 게시물의 갯수를 반환
		public abstract Integer getTotalCount() throws DAOException;
		

	
	
} // end interface
