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
	
	// Best Board
	public abstract List<BoardVO> selectBestBoard(int BoaedAmount) throws DAOException;
}
