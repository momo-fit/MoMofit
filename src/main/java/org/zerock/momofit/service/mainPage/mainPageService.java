package org.zerock.momofit.service.mainPage;

import java.util.List;

import org.zerock.momofit.domain.group.GroupVO;
import org.zerock.momofit.domain.main.BoardVO;
import org.zerock.momofit.exception.ServiceException;

public interface mainPageService {
	
	// mainPage Best Groups
	public abstract List<GroupVO> selectBestGroups() throws ServiceException; 
	
	// mainPage New Groups
	public abstract List<GroupVO> selectNewGroups() throws ServiceException; 
	
	// mainPage free board
	public abstract List<BoardVO> selectBestFreeBoard() throws ServiceException; 

	// mainPage tip board
	public abstract List<BoardVO> selectBestTipBoard() throws ServiceException; 
	
	// mainPage pic board
	public abstract List<BoardVO> selectBestPicBoard() throws ServiceException; 
	
	// mainPage trade Board
	public abstract List<BoardVO> selectBestTradeBoard() throws ServiceException; 
} // end interface
