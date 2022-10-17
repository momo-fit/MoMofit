package org.zerock.momofit.controller.chat;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.zerock.momofit.domain.chat.GroupChatDomain.ChatNoticeVO;
import org.zerock.momofit.domain.chat.GroupChatVO;
import org.zerock.momofit.exception.ControllerException;
import org.zerock.momofit.service.chat.GroupChatService;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@RequiredArgsConstructor
@Log4j2

@RestController
@RequestMapping("/chat/")
public class ChatRestController {

	private final GroupChatService groupChatService;
	
	
	// 1. Group정보와 Group에 속한 멤버 정보 획득
	@GetMapping(
			value = "/rooms/{room}",
			produces = {
					MediaType.APPLICATION_JSON_VALUE
			})
	public ResponseEntity<GroupChatVO> getGroupInfoWithMembers  (
			@PathVariable("room") int room
			) throws ControllerException {
		log.trace("getGroupinfoWithMember({}) invoked.", room);
		
		try {
			
			GroupChatVO vo = this.groupChatService.getGroupInfo(room);
			
		
			return new ResponseEntity<>(vo, HttpStatus.OK);
			
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);	// 500번 CODE
		} // try-catch
		
	} // getGroupInfoWithMembers
	
	
	// 2. 채팅 공지사항 삭제
	@DeleteMapping(
			value = "/notices/{notice}",
			produces = {
					MediaType.TEXT_PLAIN_VALUE
			}
			)
	public ResponseEntity<String> removeChatNotice(
			@PathVariable("notice") int group_notice_no
			) throws ControllerException {
		log.trace("removeChatNotiace({}) invoked.");
		
		try {
			
			boolean result = this.groupChatService.deleteMyChatNoticeList(group_notice_no);
			
			
			return new ResponseEntity<>(result ? String.valueOf(group_notice_no) : "", HttpStatus.OK);
			
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);	// 500번 CODE
		}
		
	} // removeChatNotice
	
	// 3. 채팅 공지사항 등록
	@PostMapping(
			value ="/insert",
			consumes = "application/json",
			produces = {MediaType.TEXT_PLAIN_VALUE}
			)
	public ResponseEntity<String> createChatNotice(
			@RequestBody ChatNoticeVO vo
			) throws ControllerException{
		log.trace("createChatNotice() invoked.");
		
		try {
			
			boolean result = this.groupChatService.insertMyChatNotice(vo);
			
			return result ? new ResponseEntity<>("SUCCESS", HttpStatus.OK)		// 200번 OK CODE
					: new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);	// 500번 CODE
			
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);	// 500번 CODE
		}
		

	} // createChatNotice

} // end class
