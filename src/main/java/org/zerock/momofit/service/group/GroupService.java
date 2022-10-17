package org.zerock.momofit.service.group;

import java.util.List;

import org.zerock.momofit.domain.group.Criteria;
import org.zerock.momofit.domain.group.GroupDTO;
import org.zerock.momofit.domain.group.GroupVO;
import org.zerock.momofit.exception.DAOException;
import org.zerock.momofit.exception.ServiceException;

public interface GroupService {

	// 1. 전체목록
	public abstract List<GroupVO> getList() throws ServiceException, DAOException;
	
	// 1-2. 페이징 처리된 게시글 목록
	public abstract List<GroupVO> getListPerPage(Criteria cri) throws ServiceException;
	
	// 1-3. Best 글 (좋아요순) 목록
	public abstract List<GroupVO> likeSelect();
	
	// 2. 새로운 게시글 등록
	public abstract boolean register(GroupDTO dto) throws ServiceException, DAOException;
	
	// 2-2. 이미지 데이터 반환
	public abstract GroupDTO getAttach(Integer group_no);
	
	// 3. 특정 게시글 상세조회
	public abstract GroupDTO detail(Integer group_no) throws ServiceException, DAOException;
	
	// 4. 기존 게시글 수정
	public abstract boolean modify(GroupDTO dto) throws ServiceException, DAOException; 
	
	// 5. 기존 게시글 삭제
	public abstract boolean remove(Integer group_no) throws ServiceException, DAOException;
	
	// 6. 총 게시글 개수 조회
	public abstract int getTotal() throws ServiceException;
	
	// 7. 모임 참가
	public abstract boolean participate(Integer group_no, Integer user_no) throws ServiceException;
	
	
}
