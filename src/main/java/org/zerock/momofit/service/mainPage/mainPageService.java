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

	// mainPage Best Board
	public abstract List<BoardVO> selectBestBoard() throws ServiceException; 
	
} // end interface
