package org.zerock.momofit.domain.chat;

import java.util.Date;
import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class GroupChatDomain {

	
	@Data
	@AllArgsConstructor
	@NoArgsConstructor
	@Builder
	public static class ConnectionUser {
		

		private Integer user_no;
		private String nickname;
		private String room;
		

	} // InnerClass - ConnectionUser
	
	@Data
	@NoArgsConstructor
	@AllArgsConstructor
	@Builder
	public static class ChatVO {
		
		private Integer user_no;
		
		private String nickname;
		
		private String message;
		
		private String filePath;
		
		// Message Type
		// 0 : ENTER, 1 : MESSAGE, 2 : QUIT, 3 : Register Notice
		private Integer type;
		
		// 현재 채팅방에 접속하고 있는 User정보
		private Map<String, ConnectionUser> connectionUsers; 
		
		
	} // InnerClass - ChatDTO
	
	
	@Data
	@NoArgsConstructor
	@AllArgsConstructor
	@Builder
	public static class ChatNoticeVO {
		
		private Integer chat_notice_no;
		private Integer group_no;
		private String chat_notice_context;
		private Date chat_notice_time;
	
	} // InnerClass - ChatNoticeVO
	
} // end class
