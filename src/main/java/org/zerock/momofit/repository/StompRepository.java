package org.zerock.momofit.repository;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;
import org.zerock.momofit.domain.chat.GroupChatDomain.ConnectionUser;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;


@NoArgsConstructor

@Component
@Getter
public class StompRepository {

	
	// 현재 접속한 유저들 정보
	// ( key : room , value : ( key : session ID , value : ConnectionUser ) )  
	private Map<String, Map<String, ConnectionUser>> chatManager = Collections.synchronizedMap(new HashMap<>());
	
	// 접속한 User가 현재 어떤 방을 구독하고 있는지 수집
	// ( key : session ID, value : ConnectionUser )
	private Map<String, ConnectionUser> userManager =  Collections.synchronizedMap(new HashMap<>());



} // end class
