package org.zerock.momofit.mapper.mainPageMapper;

import java.util.List;

import org.zerock.momofit.domain.group.GroupVO;
import org.zerock.momofit.domain.main.BoardVO;
import org.zerock.momofit.exception.DAOException;

public interface mainPageMappers {
	
	// best 모임 검색
	public abstract List<GroupVO> bestGroupSelect(int amount) throws DAOException;
	
	// NEW 모임
	public abstract List<GroupVO> newGroupSelect(int amount) throws DAOException;
	
	// free board
	public abstract List<BoardVO> freeBoardBestSelect (int BoaedAmount) throws DAOException;
	
	// tip board
	public abstract List<BoardVO> tipBoardBestSelect(int BoaedAmount) throws DAOException;
	
	// pic board
	public abstract List<BoardVO> picBoardBestSelect(int BoaedAmount) throws DAOException;
	
	// trade Board
	public abstract List<BoardVO> tradeBoardBestSelect(int BoaedAmount) throws DAOException;
}
