package org.zerock.momofit.listener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionConnectedEvent;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;
import org.zerock.momofit.domain.chat.GroupChatDomain;
import org.zerock.momofit.domain.chat.GroupChatDomain.ConnectionUser;
import org.zerock.momofit.repository.StompRepository;

import lombok.extern.log4j.Log4j2;

@Log4j2

@Component
public class WebSocketEventListener {

	
	private SimpMessagingTemplate simpMessagingTemplate;
	private StompRepository stompRepository;
	
	
	@Autowired
    public WebSocketEventListener(
    		SimpMessagingTemplate simpMessagingTemplate, 
    		StompRepository stompRepository) {
		this.simpMessagingTemplate = simpMessagingTemplate;
		this.stompRepository = stompRepository;
	} // Constructor DI

	
	// Clinet의 Connect감지
	@EventListener
    public void handleWebSocketConnectListener(SessionConnectedEvent event) {
        log.trace("handleWebSocketConnectListener({}) invoked.", event);

    } // handleWebSocketConnectListener
	
	// Clinet의 DisConnect감지
    @EventListener
    public void handleWebSocketDisconnectListener(SessionDisconnectEvent event) {
        StompHeaderAccessor headerAccessor = StompHeaderAccessor.wrap(event.getMessage());
        log.trace("handleWebSocketDisconnectListener({}) invoked.", event);
        
        String sessionId = headerAccessor.getSessionId();
        
        // Step.1 : Disconnect한 USER 정보 획득
        ConnectionUser user = this.stompRepository.getUserManager().get(sessionId);
        
        // Step.2 : 유저가 구독하고 있는 Room 정보 / user_no 획득
        String room = user.getRoom();
        String nickname = user.getNickname();
        Integer user_no = user.getUser_no();
        
        
        // Step.3 : 유저가 구독한 Room에 메시지 전달
        // - 해당유저가 DisConnect
        GroupChatDomain.ChatVO dto = GroupChatDomain.ChatVO.builder()
        		.user_no(user_no)
        		.nickname(nickname)
        		.type(2)
        		.build();
        
		simpMessagingTemplate.convertAndSend("/topic/" + room, dto);

    }
	
} // end class
