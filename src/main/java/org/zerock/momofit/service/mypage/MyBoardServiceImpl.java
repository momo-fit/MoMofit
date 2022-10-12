package org.zerock.momofit.service.mypage;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.zerock.momofit.domain.mypage.BoardImgVO;
import org.zerock.momofit.domain.mypage.Criteria;
import org.zerock.momofit.domain.mypage.MyBoardVO;
import org.zerock.momofit.exception.ServiceException;
import org.zerock.momofit.mapper.mypage.MyBoardMapper;
import org.zerock.momofit.util.FileUploadUtil;

import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;

@Log4j2
@NoArgsConstructor

@Service
public class MyBoardServiceImpl implements MyBoardService {

	@Setter(onMethod_= {@Autowired})
	private MyBoardMapper myboardMapper;
	
	//---------------------------------------------
	//1. 페이징 처리된 "내 글" 리스트 조회"
	//---------------------------------------------
	@Override
	public List<MyBoardVO> getBoardList(Criteria cri) throws ServiceException {
		log.trace("getBoardList() invoked.");
		
		try {
			return this.myboardMapper.getListWithPaging(cri);
			
			
		} catch (Exception e) {
			throw new ServiceException(e);
		}

	} // getBoardList
	
	//---------------------------------------------
	//2. "내 글" 총 개수 구하기
	//---------------------------------------------
	@Override
	public int getBoardCount(Criteria cri) throws ServiceException {
		log.trace("getBoardCount({}) invoked.");
		
		try {		
			return this.myboardMapper.getMyBoardCount(cri);		
		} catch (Exception e) {
			throw new ServiceException(e);
		} // try-catch
	} // getBoardCount

	
	//---------------------------------------------
	//3. "내 글" 삭제하기
	//---------------------------------------------
	@Transactional(rollbackFor = Exception.class)
	@Override
	public boolean removeArticle(int board_no) throws ServiceException {
		log.trace("removeArticle() invoked.");
		try {
			
			// Step.1 : 게시물에 담겨진 게시물 정보 획득
			List<BoardImgVO> imgList = this.myboardMapper.selectBoardImgList(board_no);
			log.info("\t+ imgList : {}", imgList);
			
			// Step.2 : 게시글 삭제
			int result = this.myboardMapper.deleteMyboardArticle(board_no);
			
			// Step.3 : 게시판 이미지 삭제
			if(imgList != null && !imgList.isEmpty()) {			
				this.myboardMapper.deleteBoardImg(board_no);
				
				// Step.4 : 물리적 파일 삭제
				if(result == 1) {
					
					imgList.forEach(vo -> {	
						FileUploadUtil.deleteFile(vo.getPath(), vo.getBoard_img_name(), vo.getTemp());					
					});

				} // if : 게시글 삭제가 성공했을 경우에만 (물리적 파일 삭제는 Rollback불가)
							
			} // if : 게시판 이미지 삭제

			return result == 1;	
		} catch (Exception e) {
			throw new ServiceException(e);
		} // try-catch
		
	} // removeArticle

} // end class
