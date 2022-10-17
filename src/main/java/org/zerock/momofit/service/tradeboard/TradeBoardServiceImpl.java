package org.zerock.momofit.service.tradeboard;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.zerock.momofit.domain.tradeboard.Criteria;
import org.zerock.momofit.domain.tradeboard.TradeBoardDTO;
import org.zerock.momofit.domain.tradeboard.TradeBoardVO;
import org.zerock.momofit.exception.ServiceException;
import org.zerock.momofit.mapper.tradeboard.TradeBoardMapper;

import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;

@Log4j2
@NoArgsConstructor

@Service
public class TradeBoardServiceImpl implements TradeBoardService {
	
	@Setter(onMethod_= @Autowired)
	private TradeBoardMapper mapper;

	
	
	@Override
	public List<TradeBoardVO> getList() throws ServiceException {
		log.trace("getList() invoked.");
		
		try {
			return this.mapper.selectAllList();
		} catch(Exception e) {
			throw new ServiceException(e);
		} // try-catch
	} // getList

	@Override
	public boolean register(TradeBoardDTO dto) throws ServiceException {
		log.trace("register({}) invoked.", dto);
		
		// 핵심 비지니스 로직 구현
		try {
			return this.mapper.insertSelectKey(dto) == 1;
		} catch(Exception e) {
			throw new ServiceException(e);
		} // try-catch
	} // register

	@Override
	public boolean modify(TradeBoardDTO dto) throws ServiceException {
		log.trace("modify({}) invoked.", dto);
		
		// 핵심 비지니스 로직 구현
		try {
			return this.mapper.update(dto) == 1;
		} catch(Exception e) {
			throw new ServiceException(e);
		} // try-catch
	} // modify

	@Override
	public boolean remove(TradeBoardDTO dto) throws ServiceException {
		log.trace("remove({}) invoked.", dto);
		
		// 핵심 비지니스 로직 구현
		try {
			return this.mapper.delete(dto.getBoard_no()) == 1;
		} catch(Exception e) {
			throw new ServiceException(e);
		} // try-catch
	} // remove

	@Override
	public TradeBoardVO get(TradeBoardDTO dto) throws ServiceException {
		log.trace("get({}) invoked.", dto);
	
		
		try {
			return this.mapper.select(dto);
		} catch(Exception e) {
			throw new ServiceException(e);
		} // try-catch
	}

	
	
	@Override
	public List<TradeBoardVO> getListPerPage(Criteria cri) throws ServiceException {
		log.trace("getListPerPage({}) invoked.", cri);
		
		try {
			return this.mapper.selectListWithPaging(cri);
		} catch(Exception e) {
			throw new ServiceException(e);
		} // try-catch
	} // getListPerPage
	

	@Override
	public int getTotal() throws ServiceException {
		log.trace("getTotal() invoked.");
		
		try {
			return this.mapper.getTotalCount();
		} catch(Exception e) {
			throw new ServiceException(e);
		} // try-catch
	} // getTotal

	
	
} // end class
