package org.zerock.momofit.controller.chat;

import javax.servlet.http.HttpSession;

import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Controller;
import org.zerock.momofit.domain.chat.ChatDTO;
import org.zerock.momofit.domain.signUp.UserDTO;

import lombok.extern.log4j.Log4j2;


@Log4j2
@Controller
public class StompController {
 
	@MessageMapping("/{room}")
	@SendTo("/topic/{room}")
	public ChatDTO sendMessage(
			@DestinationVariable long room, 
			String message,
			SimpMessageHeaderAccessor headerAccessor
			) throws Exception {
		log.trace("sendMessage({}, {}) invoked.", room ,message);

		// Interceptor에서 HttpSession객체를 WebSocketSession에 저장한 Session 획득
		HttpSession session = (HttpSession) headerAccessor.getSessionAttributes().get("session");
		log.info("\t+ session : {}", session);

		// (코드수정필요) SessionScope에서 UserVO 객체 획득
		UserDTO userDTO = (UserDTO) session.getAttribute("userDTO");
		log.info("\t+ udto : {}", userDTO);
		
		// subcribe한 Client에게 전송할 ChatDTO 생성
		ChatDTO dto = new ChatDTO();
		

		dto.setMessage(message);
		dto.setNickname(userDTO.getNickname());
		dto.setUser_no(userDTO.getUser_no());
		
		
		return dto;
		
	} // sendMessage
	
	
} // end class
