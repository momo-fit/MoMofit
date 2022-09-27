package org.zerock.momofit.service.chat;

import java.util.List;

import org.springframework.stereotype.Service;
import org.zerock.momofit.domain.chat.GroupChatVO;
import org.zerock.momofit.exception.ServiceException;
import org.zerock.momofit.mapper.chat.GroupChatMapper;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;


@AllArgsConstructor
@Log4j2

@Service
public class GroupChatServiceImpl implements GroupChatService {
	
	private GroupChatMapper groupChatMapper;
	
	// 1.  채팅방 입장 시, Group정보와 속해있는 멤버정보 획득
	@Override
	public GroupChatVO getGroupInfo(int group_no) throws ServiceException {
		log.trace("getGroupInfo() invoked.");
		
		try {

			return this.groupChatMapper.selectGroupInfoWithMembers(group_no);
			
			
		} catch (Exception e) {
			throw new ServiceException(e);
		} // try-catch
		
	} // getGroupInfo

	// 2. 내가 속한 Group List 얻기
	@Override
	public List<GroupChatVO> getMyGroupList(int group_no) throws ServiceException {
		log.trace("getMyGroupList() invoked.");
		
		try {
			
			return this.groupChatMapper.selectMyGroupList(group_no);
			
		} catch (Exception e) {
			throw new ServiceException(e);
		} // try-catch
		
	} // getMyGroupList
	
	

	
} // end class
