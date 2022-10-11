package org.zerock.momofit.service.picboard;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.zerock.momofit.domain.picboard.Criteria;
import org.zerock.momofit.domain.picboard.PicBoardCommDTO;
import org.zerock.momofit.domain.picboard.PicBoardCommVO;
import org.zerock.momofit.domain.picboard.PicBoardDTO;
import org.zerock.momofit.domain.picboard.PicBoardVO;
import org.zerock.momofit.domain.picboard.board_imgDTO;
import org.zerock.momofit.exception.DAOException;
import org.zerock.momofit.exception.ServiceException;
import org.zerock.momofit.mapper.picboard.PicBoardMapper;

import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;

@Log4j2
@NoArgsConstructor 
@Service
public class PicBoardServiceImpl implements PicBoardService {
	
	@Setter(onMethod_=@Autowired)
	private PicBoardMapper PicBoardMapper;
	
	@Transactional
	@Override
	public boolean register(PicBoardDTO dto) throws ServiceException {
		log.trace("insert({}) invoked",dto); 
		try {
			// 게시물을 저장
			this.PicBoardMapper.insertSelectKey(dto);
			
			// 첨부 이미지가 없을 경우 게시물만 저장
			if(dto.getImageList() == null || dto.getImageList().size() <=0) {
				return true;
			}
			
			// 첨부 이미지가 있으면 얘도 저장
			dto.getImageList().forEach(oneimg -> {
				log.trace(" *** dto.getBoard_no() : " + dto.getBoard_no());
				oneimg.setBoard_no(dto.getBoard_no());
				try {
					this.PicBoardMapper.insert_img(oneimg);
				} catch (DAOException e) {
					e.printStackTrace();
				}
			});
			
			// 게시물도 저장되고 첨부 이미지도 저장되면 true를 반환
			return true;
		}catch(Exception e){
			throw new ServiceException(e);
		}
	}//작성


	@Override
	public boolean modify(PicBoardDTO dto) throws ServiceException {
		log.trace("modify({}) invoked",dto); 
		try {
			return this.PicBoardMapper.update(dto)==1; //참
		}catch(Exception e){
			throw new ServiceException(e);
		}
	}//수정
	
	@Override
	public PicBoardVO get(PicBoardDTO dto) throws ServiceException {
		log.trace("get({}) invoked",dto);
		try {
			this.PicBoardMapper.plusHits(dto);  //조회수 
			return this.PicBoardMapper.select(dto); 
		}catch(Exception e){
			throw new ServiceException(e);
		}
	}//get 상세조회

	

	@Override
	public List<PicBoardVO> getList() throws ServiceException{
		
		try {
			return this.PicBoardMapper.selectAllList();
		}catch(Exception e) {
			throw new ServiceException(e);
		 }
	} //목록조회
	

	@Override
	public List<PicBoardVO> getListWithPaging(Criteria cri) throws ServiceException {
		log.trace("gitListWithPaging({})",cri);
		try {
			return this.PicBoardMapper.selectListWithPaging(cri);
		}catch(Exception e) {
			throw new ServiceException(e);
		}
	} //페이징처리
	
 
	@Override
	public boolean remove(PicBoardDTO dto) throws ServiceException {
		
		try {
			return this.PicBoardMapper.delete(dto.getBoard_no())==1;
		
		}catch(Exception e) {
			throw new ServiceException(e);
		}  
	}//remove 삭제


	@Override
	public int getTotal(Criteria cri) throws ServiceException { 
		
		try {
			return this.PicBoardMapper.getTotalCount(cri); //검색기능구현할때 페이지를 알아야해서 매개변수에 cri추가해줌
		}catch(Exception e){
			throw new ServiceException(e);
		}
	}//총 게시글 개수 조회


	@Override
	public List<board_imgDTO> imageList(int board_no) throws ServiceException {
		
		try {
			return this.PicBoardMapper.imageList(board_no);
		} catch (Exception e) {
			
			throw new ServiceException(e);
		}
	}//해당 게시물의 업로드 된 파일 조회하기 

	
	
	
	
	

	
	

}
