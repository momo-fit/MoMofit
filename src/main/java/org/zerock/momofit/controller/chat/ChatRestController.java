package org.zerock.momofit.controller.chat;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.zerock.momofit.domain.chat.GroupChatVO;
import org.zerock.momofit.exception.ControllerException;
import org.zerock.momofit.service.chat.GroupChatService;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;

@AllArgsConstructor
@Log4j2

@RestController
@RequestMapping("/chat/")
public class ChatRestController {

	private GroupChatService groupChatService;
	
	
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
			throw new ControllerException(e);
		} // try-catch
		
	} // getGroupInfoWithMembers
	
	
	
	
	
} // end class
