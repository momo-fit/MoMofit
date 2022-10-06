package org.zerock.momofit.service.tipboard;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.zerock.momofit.domain.tipboard.Criteria;
import org.zerock.momofit.domain.tipboard.TipBoardDTO;
import org.zerock.momofit.domain.tipboard.TipBoardVO;
import org.zerock.momofit.exception.ServiceException;
import org.zerock.momofit.mapper.tipboard.TipBoardMapper;

import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;

@Log4j2
@NoArgsConstructor

@Service
public class TipBoardServiceImpl implements TipBoardService {
	
	// 주입
	@Setter(onMethod_=@Autowired)
	private TipBoardMapper mapper;

	// 1. 게시글 전체목록 획득 (게시글 번호의 역순으로)
	@Override
	public List<TipBoardVO> getList() throws ServiceException {
		log.trace("getList() invoked.");
		
		try {
			return this.mapper.selectAllList();
		} catch (Exception e) {
			throw new ServiceException(e);
		} // try-catch
		
	} // getList
	
	// 2. 새로운 게시글 등록
	@Override
	public boolean register(TipBoardDTO dto) throws ServiceException {
		log.trace("register({}) invoked.", dto);
		
		try {
			return this.mapper.insert(dto) == 1;
		} catch (Exception e) {
			throw new ServiceException(e);
		} // try-catch
		
	} // register
	
	// 3. 기존 게시글 수정
	@Override
	public boolean modify(TipBoardDTO dto) throws ServiceException {
		log.trace("modify({}) invoked.", dto);
		
		try {
			return this.mapper.update(dto) == 1;
		} catch (Exception e) {
			throw new ServiceException(e);
		} // try-catch
		
	} // modify
	
	// 4. 기존 게시글 삭제
	@Override
	public boolean remove(TipBoardDTO dto) throws ServiceException {
		log.trace("remove({}) invoked.", dto);
		
		try {
			return this.mapper.delete(dto.getBoard_no()) == 1;
		} catch (Exception e) {
			throw new ServiceException(e);
		} // try-catch
		
	} // remove

	
	// 5. 기존 게시글 상세조회
	@Override
	public TipBoardVO get(TipBoardDTO dto) throws ServiceException {
		log.trace("get({}) invoked.", dto);
		
		
		try {
			return this.mapper.select(dto);
		} catch (Exception e) {
			throw new ServiceException(e);
		} // try-catch
		
	}// get
	
	
	// 1-1. 페이징 처리된 게시글 목록 획득
	@Override
	public List<TipBoardVO> getListPerPage(Criteria cri) throws ServiceException {
		log.trace("getListPerPage({}) invoked.", cri);
		
		try {
			return this.mapper.selectListWithPaging(cri);
		} catch (Exception e) {
			throw new ServiceException(e);
		} // try-catch
		
	} // getListPerPage
	
	
	// 6. 총 게시물 개수 조회
	@Override
	public int getTotal() throws ServiceException {
		log.trace("getTotal({}) invoked.");
		
		try {
			return this.mapper.getTotalCount();
		} catch (Exception e) {
			throw new ServiceException(e);
		} // try-catch
		
	} // getTotal
	
} // end class
