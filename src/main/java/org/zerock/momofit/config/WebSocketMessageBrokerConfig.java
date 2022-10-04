package org.zerock.momofit.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.ChannelRegistration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;
import org.zerock.momofit.interceptor.GroupChatHttpHandshakeInterceptor;
import org.zerock.momofit.interceptor.StompMessageInterceptor;

import lombok.AllArgsConstructor;

@Configuration	
@EnableWebSocketMessageBroker
@AllArgsConstructor
public class WebSocketMessageBrokerConfig implements WebSocketMessageBrokerConfigurer {

	
	private StompMessageInterceptor stompMessageIntercetor;
	
	// StompEndPoint에 대한 설정
	@Override
	public void registerStompEndpoints(StompEndpointRegistry registry) {
		
		registry.addEndpoint("/myGroupStomp")
				.addInterceptors(new GroupChatHttpHandshakeInterceptor())
				.withSockJS();
		
	} // registerStompEndpoints

	@Override
	public void configureMessageBroker(MessageBrokerRegistry registry) {
		
		registry.enableSimpleBroker("/topic");
		registry.setApplicationDestinationPrefixes("/chat");

	} // configureMessageBroker

	
	// 메시지를 브로커에게 전달하기 전 실행되는 Interceptor
	@Override
	public void configureClientInboundChannel(ChannelRegistration registration) {
		
		registration.interceptors(stompMessageIntercetor);
		
	} // configureClientInboundChannel
	

} // end class
