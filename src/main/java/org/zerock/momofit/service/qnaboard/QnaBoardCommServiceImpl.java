package org.zerock.momofit.service.qnaboard;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.zerock.momofit.domain.picboard.Criteria;
import org.zerock.momofit.domain.picboard.PicBoardCommDTO;
import org.zerock.momofit.domain.qnaboard.QnaBoardCommDTO;
import org.zerock.momofit.mapper.qnaboard.QnaBoardCommMapper;

import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;

@Log4j2
@NoArgsConstructor 
@Service
public class QnaBoardCommServiceImpl implements QnaBoardCommService {
	
	@Setter(onMethod_=@Autowired)
	private QnaBoardCommMapper QnaBoardCommMapper;
	

	@Override
	public QnaBoardCommDTO getComm(Integer comm_no) {

			return this.QnaBoardCommMapper.readComm(comm_no);

	}//댓글 조회 
		
	@Override
	public Integer registerComm(QnaBoardCommDTO dto){
	
			log.info("*** Service dto : ", dto);
			return this.QnaBoardCommMapper.insertComm(dto);

	}//댓글 작성하기 

	@Override
	public Integer removeComm(Integer qna_comm_no){

			return this.QnaBoardCommMapper.deleteComm(qna_comm_no);

	}//댓글 삭제하기
	

	@Override
	public Integer modifyComm(QnaBoardCommDTO dto){

			return this.QnaBoardCommMapper.updateComm(dto);

	}//댓글 수정하기 

	@Override
	public List<QnaBoardCommDTO> getCommList(Integer qna_no, Criteria cri){

			return this.QnaBoardCommMapper.readCommList(qna_no, cri);

	}//게시글에 대한 댓글 조회 
	
	




	



	
	
	
	
	
	
	
	
	
	

	
	

}
