package org.zerock.momofit.service.qnaboard;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.zerock.momofit.domain.picboard.board_imgDTO;
import org.zerock.momofit.domain.qnaboard.Criteria;
import org.zerock.momofit.domain.qnaboard.QnaBoardDTO;
import org.zerock.momofit.domain.qnaboard.QnaBoardVO;
import org.zerock.momofit.domain.qnaboard.qna_imgDTO;
import org.zerock.momofit.exception.ServiceException;
import org.zerock.momofit.mapper.qnaboard.QnaBoardMapper;

import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;
@Log4j2
@NoArgsConstructor 
@Service
public class QnaBoardServiceImpl implements QnaBoardService {
	
	@Setter(onMethod_= {@Autowired})
	private QnaBoardMapper QnaBoardMapper;
	

	@Override
	public List<QnaBoardVO> getList(Criteria cri) throws ServiceException {
		
		try {
			return this.QnaBoardMapper.SelectList(cri);
		} catch (Exception e) {
			throw new ServiceException(e);
			}
	}//목록 조회
	


	@Override
	public boolean register(QnaBoardDTO dto) throws ServiceException {
		try {
			return this.QnaBoardMapper.insert(dto)==1;	
		}catch(Exception e) {
			throw new ServiceException(e);
		}
		
	}//게시글 작성 
	
	@Override
	public boolean modify(QnaBoardDTO dto) throws ServiceException {
		log.trace("modify({}) invoked",dto); 
		try {
			return this.QnaBoardMapper.update(dto)==1; //참
		}catch(Exception e){
			throw new ServiceException(e);
		}
	}//수정
	
	@Override
	public QnaBoardVO get(QnaBoardDTO dto) throws ServiceException {
		log.trace("get({}) invoked",dto);
		try { 
			return this.QnaBoardMapper.select(dto);
		}catch(Exception e){
			throw new ServiceException(e);
		}
	}//get 상세조회
	
	
	@Override
	public boolean remove(QnaBoardDTO dto) throws ServiceException {
		
		try {
			return this.QnaBoardMapper.delete(dto.getQna_no())==1;
		
		}catch(Exception e) {
			throw new ServiceException(e);
		}  
	}//remove 삭제


	@Override
	public int getTotal(Criteria cri) throws ServiceException { 
		
		try {
			return this.QnaBoardMapper.getTotalCount(cri); //검색기능구현할때 페이지를 알아야해서 매개변수에 cri추가해줌
		}catch(Exception e){
			throw new ServiceException(e);
		}
	}//총 게시글 개수 조회


	@Override
	public List<qna_imgDTO> imageList(int qna_no) throws ServiceException {
		
		try {
			return this.QnaBoardMapper.imageList(qna_no);
		} catch (Exception e) {
			
			throw new ServiceException(e);
		}
	}//해당 게시물의 업로드 된 파일 조회하기 
	
	
	
	

}
