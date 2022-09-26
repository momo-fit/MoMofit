package org.zerock.momofit.service.picboard;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.zerock.momofit.domain.picboard.Criteria;
import org.zerock.momofit.domain.picboard.PicBoardDTO;
import org.zerock.momofit.domain.picboard.PicBoardVO;
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
	
	
	@Override
	public boolean register(PicBoardDTO dto) throws ServiceException {
		log.trace("insert({}) invoked",dto); 
		try {
			return this.PicBoardMapper.insert(dto)==1; //참
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
			this.PicBoardMapper.plusHits(dto);
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
	} //총 게시글 개수 조회


//	@Override
//	public PicBoardVO plusHits(PicBoardDTO dto) throws ServiceException {
//		try {
//		return this.PicBoardMapper.plusHits(null);
//		
//		}catch(Exception e) {
//			throw new ServiceException(e);
//		}  
//	}//게시글 조회수 증가


		




}
