package org.zerock.momofit.service.mainPage;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.zerock.momofit.domain.group.GroupVO;
import org.zerock.momofit.domain.main.BoardVO;
import org.zerock.momofit.exception.ServiceException;
import org.zerock.momofit.mapper.mainPageMapper.mainPageMappers;

import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;

@NoArgsConstructor
@Log4j2

@Service
public class mainPageServiceImpl implements mainPageService {
	
	@Setter(onMethod_ = {@Autowired})
	private mainPageMappers mapper;
	
	private int amount = 8;
	private int BoaedAmount = 5;
	
	@Override
	public List<GroupVO> selectBestGroups() throws ServiceException {
		log.trace("selectBestGroups() invoked.");
				
		
		try {
			return this.mapper.bestGroupSelect(this.amount);
		}catch (Exception e) {
			throw new ServiceException(e);
		} // try-catch	
		
	} // selectBestGroups

	@Override
	public List<GroupVO> selectNewGroups() throws ServiceException {
		log.trace("selectBestGroups() invoked.");
		
		try {
			return this.mapper.newGroupSelect(this.amount);
		}catch (Exception e) {
			throw new ServiceException(e);
		} // try-catch	
	
	} // selectNewGroups

	@Override
	public List<BoardVO> selectBestFreeBoard() throws ServiceException {
		log.trace("selectBestGroups() invoked.");
		
		try {
			return this.mapper.freeBoardBestSelect(this.BoaedAmount);	
		}catch (Exception e) {
			throw new ServiceException(e);
		} // try-catch	
	} // selectBestFreeBoard

	@Override
	public List<BoardVO> selectBestTipBoard() throws ServiceException {
		log.trace("selectBestGroups() invoked.");
		
		try {
			return this.mapper.tipBoardBestSelect(this.BoaedAmount);	
		}catch (Exception e) {
			throw new ServiceException(e);
		} // try-catch	
	} // selectBestTipBoard

	@Override
	public List<BoardVO> selectBestPicBoard() throws ServiceException {
		log.trace("selectBestGroups() invoked.");
		
		try {
			return this.mapper.picBoardBestSelect(this.BoaedAmount);
		}catch (Exception e) {
			throw new ServiceException(e);
		} // try-catch	
	} // selectBestPicBoard

	@Override
	public List<BoardVO> selectBestTradeBoard() throws ServiceException {
		log.trace("selectBestGroups() invoked.");
		
		try {		
			return this.mapper.tradeBoardBestSelect(this.BoaedAmount);
		}catch (Exception e) {
			throw new ServiceException(e);
		} // try-catch	
	} // selectBestTradeBoard

} // end class
