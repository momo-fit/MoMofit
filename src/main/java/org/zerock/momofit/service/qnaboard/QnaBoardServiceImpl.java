package org.zerock.momofit.service.qnaboard;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.zerock.momofit.domain.qnaboard.Criteria;
import org.zerock.momofit.domain.qnaboard.QnaBoardDTO;
import org.zerock.momofit.domain.qnaboard.QnaBoardVO;
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
	
	

}
