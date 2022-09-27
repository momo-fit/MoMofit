package org.zerock.momofit.mapper.chat;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.zerock.momofit.domain.chat.GroupChatVO;
import org.zerock.momofit.exception.DAOException;

public interface GroupChatMapper {

	// 1. 채팅방 입장 시, Group정보와 속해있는 멤버정보 획득
	public abstract GroupChatVO selectGroupInfoWithMembers
			(@Param("group_no")int group_no) throws DAOException;
	
	// 2. 접속한 User정보로, 나의 Group List 얻기
	public abstract List<GroupChatVO> selectMyGroupList(
			@Param("user_no")int user_no
			) throws DAOException;
	
	
	
} // end interface
