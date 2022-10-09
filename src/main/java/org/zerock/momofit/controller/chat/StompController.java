package org.zerock.momofit.controller.chat;

import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Controller;
import org.zerock.momofit.common.SharedScopeKeys;
import org.zerock.momofit.domain.chat.GroupChatDomain.ChatDTO;
import org.zerock.momofit.domain.chat.GroupChatDomain.ConnectionUser;
import org.zerock.momofit.domain.signIn.LoginVO;
import org.zerock.momofit.repository.StompRepository;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;


@Log4j2
@AllArgsConstructor

@Controller
public class StompController {
	
	private StompRepository stompRepository;
	

	
	@MessageMapping("/{room}")
	@SendTo("/topic/{room}")
	public ChatDTO sendMessage(
			@DestinationVariable long room,
			ChatDTO dto,
			SimpMessageHeaderAccessor headerAccessor
			) throws Exception {
		log.trace("sendMessage({}, {}) invoked.", room ,dto);

		// Interceptor에서 HttpSession객체를 WebSocketSession에 저장한 Session 획득
		HttpSession session = (HttpSession) headerAccessor.getSessionAttributes().get("session");
		
		
		
		// WebSocket SessionID 획득
		String sessionId = headerAccessor.getSessionId();
		log.info("\t+ session : {}", session);
		log.info("\t+ sessionId : {}", sessionId);

		// (코드수정필요) SessionScope에서 UserVO 객체 획득
		LoginVO userVO = (LoginVO) session.getAttribute(SharedScopeKeys.USER_KEY);
		log.info("\t+ udto : {}", userVO);	 	

		
		
		// 해당 Room에 접속한 User정보 객체 획득
		String targetRoom = String.valueOf(room);
		
		// (key : sessionId , value : ConnectionUser)
		// ConnectionUser : ( user_no, nickname, room )	
		Map<String, ConnectionUser> connectionUsers = 
				this.stompRepository.getChatManager().get(targetRoom);
		
		// 해당 Room에 접속한 User정보 올리기
		ConnectionUser user = connectionUsers
				.getOrDefault(sessionId, new ConnectionUser());
		
		user.setUser_no(userVO.getUser_no());
		user.setNickname(userVO.getNickname());
		user.setRoom(targetRoom);
		
		
		connectionUsers.put(sessionId, user);
		
		// subcribe한 Client에게 전송할 ChatDTO 생성
		dto.setNickname(userVO.getNickname());
		dto.setUser_no(userVO.getUser_no());
		dto.setConnectionUsers(connectionUsers);	// targetRoom에 속해 있는, User객체		

		return dto;
		
	} // sendMessage
	
	
} // end class
