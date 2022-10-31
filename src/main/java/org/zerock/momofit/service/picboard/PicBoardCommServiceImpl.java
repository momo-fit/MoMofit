package org.zerock.momofit.service.picboard;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.zerock.momofit.domain.picboard.Criteria;
import org.zerock.momofit.domain.picboard.PicBoardCommDTO;
import org.zerock.momofit.domain.picboard.PicBoardCommVO;
import org.zerock.momofit.exception.DAOException;
import org.zerock.momofit.exception.ServiceException;
import org.zerock.momofit.mapper.picboard.PicBoardCommMapper;
import org.zerock.momofit.mapper.picboard.PicBoardMapper;

import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;

@Log4j2
@NoArgsConstructor 
@Service
public class PicBoardCommServiceImpl implements PicBoardCommService {
	
	@Setter(onMethod_=@Autowired)
	private PicBoardCommMapper PicBoardCommMapper;
	

	@Override
	public PicBoardCommDTO getComm(Integer comm_no) {

			return this.PicBoardCommMapper.readComm(comm_no);

	}//댓글 조회 
		
	@Override
	public Integer registerComm(PicBoardCommDTO dto){
	
			log.info("*** Service dto : ", dto);
			return this.PicBoardCommMapper.insertComm(dto);

	}//댓글 작성하기 

	@Override
	public Integer removeComm(Integer comm_no){

			return this.PicBoardCommMapper.deleteComm(comm_no);

	}//댓글 삭제하기
	

	@Override
	public Integer modifyComm(PicBoardCommDTO dto){

			return this.PicBoardCommMapper.updateComm(dto);

	}//댓글 수정하기 

	@Override
	public List<PicBoardCommDTO> getCommList(Integer board_no, Criteria cri){

			return this.PicBoardCommMapper.readCommList(board_no, cri);

	}//게시글에 대한 댓글 조회 
	
	




	



	
	
	
	
	
	
	
	
	
	

	
	

}
