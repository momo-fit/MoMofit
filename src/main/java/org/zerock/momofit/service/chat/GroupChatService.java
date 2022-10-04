package org.zerock.momofit.service.chat;

import java.util.List;

import org.zerock.momofit.domain.chat.GroupChatDomain.ChatNoticeVO;
import org.zerock.momofit.domain.chat.GroupChatVO;
import org.zerock.momofit.exception.ServiceException;

public interface GroupChatService {

	// 1. Group정보와, 속해있는 멤버정보 획득
	public abstract GroupChatVO getGroupInfo(int group_no) throws ServiceException;
	
	// 2. 내가 속한 Group 정보 얻기
	public abstract List<GroupChatVO> getMyGroupList(int group_no) throws ServiceException;
	
	// 3. 나의 채팅 공지 1개 삭제하기
	public abstract boolean deleteMyChatNoticeList(int chat_notice_no) throws ServiceException;
	
	// 4. 나의 채팅 공지 등록하기
	public abstract boolean insertMyChatNotice(ChatNoticeVO vo) throws ServiceException;
	
	
} // interface
