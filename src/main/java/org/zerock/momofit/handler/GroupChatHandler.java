package org.zerock.momofit.handler;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import lombok.extern.log4j.Log4j2;


@Log4j2
public class GroupChatHandler extends TextWebSocketHandler {
	
	// Step.1 : 동기화처리된 접속하는 Session정보를 담기위한 List객체
	List<WebSocketSession> sessions 
		= Collections.synchronizedList(new ArrayList<>());

	
	// Client가 Server에 Connection에 연결...
	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		log.trace("afterConnectionEstablished({}) invoked.");
		
		sessions.add(session);
		
	} // afterConnectionEstablished

	
	// Clinet가 어떤 "메시지"를 발송했을 때...
	@Override
	protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
		log.trace("handleTextMessage({}, {}) invoked.", session, message);
		
		Map<String, Object> map = session.getAttributes();
		
		int user_no = 1;
		
		for(WebSocketSession sess : sessions) {
			sess.sendMessage(new TextMessage(user_no + " : " + message.getPayload()));
		}
		
	} // handleTextMessage

	
	// Client가 Server에서 Closed할때...
	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
		
		sessions.remove(session);
		
	} // afterConnectionClosed

	
	
} // end class
