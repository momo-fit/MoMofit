package org.zerock.momofit.mapper.chat;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.zerock.momofit.domain.chat.GroupChatDomain.ChatNoticeVO;
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
	
	// 3. 공지사항 리스트 획득
	public abstract List<ChatNoticeVO> selectMyChatNoticeList (
			@Param("group_no") int group_no
			) throws DAOException;
	
	// 4. 공지사항 삭제
	public abstract Integer deleteMyChatNotice(
			@Param("chat_notice_no") int chat_notice_no
			) throws DAOException;
	
	// 5. 공지사항 등록
	public abstract Integer insertMyChatNotice(
			@Param("vo")ChatNoticeVO vo
			) throws DAOException;
	

	
} // end interface
