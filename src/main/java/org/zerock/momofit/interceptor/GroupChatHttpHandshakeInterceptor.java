package org.zerock.momofit.interceptor;

import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.HandshakeInterceptor;
import org.zerock.momofit.domain.signUp.UserDTO;

import lombok.extern.log4j.Log4j2;


@Log4j2
public class GroupChatHttpHandshakeInterceptor implements HandshakeInterceptor {

	// Connection HTTP 보내기 전, HTTP Session 객체를 WebSocket Session으로 올려두는 작업
	@Override
	public boolean beforeHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler,
			Map<String, Object> attributes) throws Exception {
		log.trace("beforeHandshake() invoked.");
		
		if(request instanceof ServletServerHttpRequest) {
			ServletServerHttpRequest servletRequest = (ServletServerHttpRequest) request;
			
			// Server로 전송오는 Request속에서 HttpServletReqeust을 받고, 그 속에서 다시 HttpSession객체를 얻는다.  
			HttpSession session = servletRequest.getServletRequest().getSession();
			log.info("\t+ session : {}", session);
			
			// Login시, Session Scope에 저장한 UserDTO 객체를 얻는다. 
			// --> 임의로 Session에 집어넣었기 때문에, 실제로 코드 변경 필요 : UserVO를 획득해야함....
			UserDTO dto = (UserDTO) session.getAttribute("userDTO");
			
			log.info("UserDTO : {}", dto);

			// WebSocketSession에 앞서 저장한 HttpSession 객체를 저장
			attributes.put("session", session);
			
			
		} // if
		
		return true;
		
	} // beforeHandshake


	@Override
	public void afterHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler,
			Exception exception) {
		// TODO Auto-generated method stub
		
	} // afterHandshake

	
}
