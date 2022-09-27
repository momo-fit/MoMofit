package org.zerock.momofit.service.chat;

import java.util.List;

import org.zerock.momofit.domain.chat.GroupChatVO;
import org.zerock.momofit.exception.ServiceException;

public interface GroupChatService {

	// 1. Group정보와, 속해있는 멤버정보 획득
	public abstract GroupChatVO getGroupInfo(int group_no) throws ServiceException;
	
	// 2. 내가 속한 Group 정보 얻기
	public abstract List<GroupChatVO> getMyGroupList(int group_no) throws ServiceException;;
	
} // interface
