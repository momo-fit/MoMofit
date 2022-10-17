package org.zerock.momofit.interceptor.chat;

import java.util.HashMap;
import java.util.Map;

import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.messaging.support.ChannelInterceptor;
import org.springframework.stereotype.Component;
import org.zerock.momofit.domain.chat.GroupChatDomain.ConnectionUser;
import org.zerock.momofit.repository.StompRepository;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@AllArgsConstructor

@Component
public class StompMessageInterceptor implements ChannelInterceptor {
	
	private StompRepository stompRepository;
	
	@Override
	public void postSend(Message<?> message, MessageChannel channel, boolean sent) {
		log.trace("postSend() invoked.");
		log.info("message : {}", message);

	   	StompHeaderAccessor accessor = StompHeaderAccessor.wrap(message);

	   	// Step.1 : WebSocket sessionID와 Message의 Destination 획득
		String sessionId = accessor.getSessionId();
		String destination =accessor.getDestination();
		log.info("\t+ destination : {}", destination);
		
		// Step.2 : Destination에서 구독하고 있는 "채팅방" 획득
		String room = "";
		if(destination != null && !destination.isBlank()) {
        	// 구독한 room 획득
            room =  destination.replaceAll("[^0-9]", "");
            log.info("\t+ room : {}", room);
        }

       // Step.3 : Message의 Command(SEND / CONNECT / SUBSCRIBE / DISCONNECT) 에 따라 수행할 명령시행 
        switch (accessor.getCommand()) {
        
        	case SEND:
        		log.info("\t+ User {} Send......", sessionId);
        		break;
        
            case CONNECT:
                // 유저가 Websocket으로 connect()를 한 뒤 호출됨
            	log.info("\t+ User {} Connect......", sessionId);
                break;
                
            case SUBSCRIBE:
            	// 유저가 WebSocket으로 subscribe() 했을 때...
            	log.info("\t+ User {} subscribe room {}.....", sessionId, room);
            	
            	// 유저의 정보를 Repository에 저장
            	this.registerUser(room, sessionId);
            	break;
            	
            case DISCONNECT:
                // 유저가 Websocket으로 disconnect() 를 한 뒤 호출됨 or 세션이 끊어졌을 때 발생함
            	// (페이지 이동~ 브라우저 닫기 등)
            	log.info("\t+ User {} Disconnet......", sessionId);
            	
            	String disconnectRoom = this.stompRepository.getUserManager().get(sessionId).getRoom();
            	log.info("\t+ disconnectRoom : {}", disconnectRoom);
            	
            	Map<String, ConnectionUser> connectionUsers = this.stompRepository.getChatManager().get(disconnectRoom);
            	
            	connectionUsers.remove(sessionId);        	
            	this.stompRepository.getUserManager().remove(sessionId);
                break;
                
            default:	
                break;
        } // switch
        
	} // postSend

	
	// 구독한 User를 StompRepository에 저장
	public void registerUser(String room, String sessionId) {


    	if(this.stompRepository.getChatManager().containsKey(room)) {
    		
    		Map<String, ConnectionUser> connectionUsers = this.stompRepository.getChatManager().get(room);
  

    		ConnectionUser user = connectionUsers
    				.getOrDefault(sessionId, new ConnectionUser());   		
    		
    		user.setRoom(room);   		
    		connectionUsers.put(sessionId, user);
    			
    		this.stompRepository.getChatManager().put(room, connectionUsers);
    		this.stompRepository.getUserManager().put(sessionId, user);
    		
    	} else {
    		
    		Map<String, ConnectionUser> connectionUsers = new HashMap<>();

    		ConnectionUser user = connectionUsers
    				.getOrDefault(sessionId, new ConnectionUser());
    		
    		user.setRoom(room);
    		connectionUsers.put(sessionId, user);
 		
    		this.stompRepository.getChatManager().put(room, connectionUsers);
    		this.stompRepository.getUserManager().put(sessionId, user);
    		
    	} // if-else
		
	} // registerUser

} // end class
