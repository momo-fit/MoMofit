package org.zerock.momofit.mapper.mypage;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.zerock.momofit.domain.mypage.Criteria;
import org.zerock.momofit.domain.mypage.MyGroupVO;
import org.zerock.momofit.exception.DAOException;

public interface MyGroupMapper {

	// 1. 나의 "Group" (참여한 모임 / 내가 생성한 모임) 리스트 조회
	public abstract List<MyGroupVO> getGroupListWithPaging(
			@Param("cri")Criteria cri
			) throws DAOException;
	
	// 2. 내가 참여한 그룹의 총 "개수" 구하기
	public abstract int getMyGroupCount(@Param("user_no")int user_no) throws DAOException;	
	
	// 3. 그룹의 그룹장 번호 얻기
	public abstract Integer getGroupAdminNo(@Param("group_no")int group_no) throws DAOException;
	
	// 4. Group테이블 member_cnt -1
	public abstract Integer updateGroupMemberCnt(@Param("group_no") int group_no, @Param("amount") int amount) throws DAOException;
	
	// 5. 내가 속한 그룹 나가기
	public abstract Integer quitGroup(@Param("group_no") int gorup_no, @Param("user_no") int user_no) throws DAOException;
	
} // end interface
