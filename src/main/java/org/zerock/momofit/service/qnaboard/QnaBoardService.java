package org.zerock.momofit.service.qnaboard;

import java.util.List;

import org.zerock.momofit.domain.qnaboard.Criteria;
import org.zerock.momofit.domain.qnaboard.QnaBoardDTO;
import org.zerock.momofit.domain.qnaboard.QnaBoardVO;
import org.zerock.momofit.exception.ServiceException;

public interface QnaBoardService {
	
	//게시글 목록 조회하기(페이징처리)
	public abstract List<QnaBoardVO> getList(Criteria cri) throws ServiceException;
	
	//새로운 게시글 등록하기
	public abstract boolean register(QnaBoardDTO dto) throws ServiceException;

	

}
