package org.zerock.momofit.service.chat;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;
import org.zerock.momofit.domain.chat.GroupChatDomain.ChatNoticeVO;
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
	
	// 3. 채팅 공지 1개 삭제
	@Override
	public boolean deleteMyChatNoticeList(int chat_notice_no) throws ServiceException {
		log.trace("getMyChatNoticeList() invoked.");
		
		try {
			
			return this.groupChatMapper.deleteMyChatNotice(chat_notice_no) == 1;
			
		} catch (Exception e) {
			throw new ServiceException(e);
		}
		
	} // deleteMyChatNoticeList

	// 4. 채팅 공지 1개 등록
	@Override
	public boolean insertMyChatNotice(ChatNoticeVO vo) throws ServiceException {
		log.trace("insertMyChatNotice() invoked.");
		
		try {
			
			return this.groupChatMapper.insertMyChatNotice(vo) == 1;
			
		} catch (Exception e) {
			throw new ServiceException(e);
		} // try-catch
		
		
	} // insertMyChatNotice

} // end class
