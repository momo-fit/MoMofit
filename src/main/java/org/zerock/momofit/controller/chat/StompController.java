package org.zerock.momofit.controller.chat;

import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Controller;
import org.zerock.momofit.common.SharedScopeKeys;
import org.zerock.momofit.domain.chat.GroupChatDomain.ChatVO;
import org.zerock.momofit.domain.chat.GroupChatDomain.ConnectionUser;
import org.zerock.momofit.domain.signIn.LoginVO;
import org.zerock.momofit.repository.StompRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;


@Log4j2
@RequiredArgsConstructor

@Controller
public class StompController {
	
	private final StompRepository stompRepository;
	

	
	@MessageMapping("/{room}")
	@SendTo("/topic/{room}")
	public ChatVO sendMessage(
			@DestinationVariable long room,
			ChatVO vo,
			SimpMessageHeaderAccessor headerAccessor
			) throws Exception {
		log.trace("sendMessage({}, {}) invoked.", room ,vo);

		// Interceptor에서 HttpSession객체를 WebSocketSession에 저장한 Session 획득
		HttpSession session = (HttpSession) headerAccessor.getSessionAttributes().get("session");
		
		
		
		// WebSocket SessionID 획득
		String sessionId = headerAccessor.getSessionId();
		log.info("\t+ session : {}", session);
		log.info("\t+ sessionId : {}", sessionId);

		// SessionScope에서 LoginVO 객체 획득
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
		
		// subcribe한 Client에게 전송할 ChatVO객체
		
		// 파일경로 (폴더명/임시파일명_원본파일명)
		if(userVO.getProfile_name() != null && !userVO.getProfile_name().isBlank()) {
			String filePath = userVO.getProfile_path() + "/" + userVO.getProfile_temp() + "_" + userVO.getProfile_name();
			vo.setFilePath(filePath);
		} // if : Profile 이미지 유무 확인

		vo.setNickname(userVO.getNickname());
		vo.setUser_no(userVO.getUser_no());
		vo.setConnectionUsers(connectionUsers);	// targetRoom에 속해 있는, User객체		

		return vo;
		
	} // sendMessage
	
	
} // end class
