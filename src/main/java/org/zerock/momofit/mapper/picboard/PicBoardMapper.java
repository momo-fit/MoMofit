package org.zerock.momofit.mapper.picboard;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.zerock.momofit.domain.picboard.Criteria;
import org.zerock.momofit.domain.picboard.PicBoardCommDTO;
import org.zerock.momofit.domain.picboard.PicBoardCommVO;
import org.zerock.momofit.domain.picboard.PicBoardDTO;
import org.zerock.momofit.domain.picboard.PicBoardVO;
import org.zerock.momofit.domain.picboard.board_imgDTO;
import org.zerock.momofit.exception.DAOException;

public interface PicBoardMapper {
	
	// 1. 게시판 테이블의 전체목록 조회하기
	public abstract List<PicBoardVO> selectAllList() throws DAOException;
	
	//1-1 페이징처리가 적용된 게시글 목록 조회하기 
	public abstract List<PicBoardVO> selectListWithPaging(Criteria cri) throws DAOException; //@Param("cri")
		
	// 2. 새로운 게시글을 등록하기
	
	public abstract Integer insertSelectKey(PicBoardDTO dto) throws DAOException;
	
	// 3. 기존 게시글 상세 조회하기
	public abstract PicBoardVO select(PicBoardDTO dto)throws DAOException;
	
	// 4. 기존 게시글 수정하기
	public abstract Integer update(PicBoardDTO dto)throws DAOException;
	
	// 5. 기존 게시글 삭제하기
	public abstract Integer delete(Integer qna_no) throws DAOException;
	
	// 6. 총 게시물의 갯수를 반환
	public abstract Integer getTotalCount(Criteria cri) throws DAOException;//검색기능구현할때 페이지를 알아야해서 매개변수에 cri추가해줌
	
	// 7. 게시글 조회수 올리기 
	public abstract Integer plusHits(PicBoardDTO dto)throws DAOException;
	
	// 8. 새로운 게시글 등록시 첨부파일 이미지 함께 등록
	public abstract Integer insert_img(board_imgDTO dto) throws DAOException;
	
	// 9. 해당 게시물의 업로드 된 파일 조회하기 
	public abstract List<board_imgDTO> imageList(int qna_no) throws DAOException;

}
