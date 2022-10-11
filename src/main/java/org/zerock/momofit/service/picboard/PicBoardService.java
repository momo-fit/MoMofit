package org.zerock.momofit.service.picboard;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.zerock.momofit.domain.picboard.Criteria;
import org.zerock.momofit.domain.picboard.PicBoardCommDTO;
import org.zerock.momofit.domain.picboard.PicBoardCommVO;
import org.zerock.momofit.domain.picboard.PicBoardDTO;
import org.zerock.momofit.domain.picboard.PicBoardVO;
import org.zerock.momofit.domain.picboard.board_imgDTO;
import org.zerock.momofit.exception.DAOException;
import org.zerock.momofit.exception.ServiceException;

public interface PicBoardService {
	
	//1.게시글 전체 목록 획득 (게시글 번호의 역순으로)
	public abstract List <PicBoardVO> getList() throws ServiceException;
	
	//1-1 페이징처리가 적용된 게시글 목록 조회하기 
	public abstract List<PicBoardVO> getListWithPaging(Criteria cri) throws ServiceException;
	
	//2.새로운 게시글 등록 + 게시판 등록시 파일첨부도 함께 되게하기  
	public abstract boolean register(PicBoardDTO dto) throws ServiceException;
	
	//3.기존 게시글 수정
	public abstract boolean modify(PicBoardDTO dto) throws ServiceException;
	
	//4.기존 게시글 상세조회 + ***조회수 
	public abstract PicBoardVO get(PicBoardDTO dto) throws ServiceException;
	
	//5. 기존 게시글 삭제하기
	public abstract boolean remove(PicBoardDTO dto)throws ServiceException;
	
	//6. 총 게시글 갯수 조회
	public abstract int getTotal(Criteria cri) throws ServiceException;
	
	//7. 해당 게시물의 업로드 된 파일 조회하기 
	public abstract List<board_imgDTO> imageList(int board_no) throws ServiceException;

	
	
 }
