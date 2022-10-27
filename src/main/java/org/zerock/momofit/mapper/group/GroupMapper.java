package org.zerock.momofit.mapper.group;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.zerock.momofit.domain.group.Criteria;
import org.zerock.momofit.domain.group.GroupDTO;
import org.zerock.momofit.domain.group.GroupVO;
import org.zerock.momofit.domain.group.ReplyDTO;
import org.zerock.momofit.exception.DAOException;


public interface GroupMapper {

	// 1. 게시판 테이블의 전체목록 조회
	public abstract List<GroupVO> getList() throws DAOException;
	
	// 1-2. 페이징 처리된 전체목록 조회
	public abstract List<GroupVO> getListWithPaging(Criteria cri) throws DAOException;	
	
	// 1-3. Best 글 (좋아요순) 목록
	public abstract List<GroupVO> likeSelect();

	// 2. 게시글 등록 (Create) 
	public abstract int insert(GroupDTO dto) throws DAOException;
	public abstract int insertSelectKey(GroupDTO dto) throws DAOException;		// 새로 입력된 게시글의 bno를 반환받기

	// 2-2. 이미지 데이터 반환
	public abstract GroupDTO getAttach(Integer group_no);
		
	// 3. 상세조회 (Read)
	public abstract GroupDTO read(Integer group_no) throws DAOException;

	// 4. 삭제 (Delete)
	public abstract int delete(Integer group_no) throws DAOException;		

	// 5. 수정 (Update)
	public abstract int update(GroupDTO dto) throws DAOException;		// 삭제/수정 여부 -> 0 or 1

	// 6. 총 게시물의 개수 반환
	public abstract int getTotalCount(Criteria cri) throws DAOException;
	
	// 7. 그룹멤버에 등록
	public abstract int joinGroup(@Param("group_no") int group_no, @Param("user_no") int user_no) throws DAOException;
	
    // 7-1 참가자 증가
    public abstract void updateGroupMember(@Param("group_no") Integer group_no, @Param("amount") Integer amount);
	
	// 8. 그룹 삭제 시, 그룹멤버 삭제(그룹 자체를 삭제)
	public abstract int deleteGroupMember(@Param("group_no") int group_no) throws DAOException;
	
	// 9. 유효성 검사 : 그룹에 참여하고 있는 지 확인
	public abstract Integer isParticipateGroup(@Param("group_no") int group_no, @Param("user_no") int user_no) throws DAOException;
	

}
