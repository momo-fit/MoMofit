package org.zerock.momofit.controller.chat;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.zerock.momofit.common.SharedScopeKeys;
import org.zerock.momofit.domain.chat.GroupChatVO;
import org.zerock.momofit.domain.signIn.LoginVO;
import org.zerock.momofit.domain.signUp.UserDTO;
import org.zerock.momofit.exception.ControllerException;
import org.zerock.momofit.service.chat.GroupChatService;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;


@Log4j2
@AllArgsConstructor

@RequestMapping("/group/")
@Controller
public class ChatController {
	
	private GroupChatService groupChatService;
	
	@GetMapping("/chat")
	public String get(
			@RequestParam(value="group_no")Integer group_no,
			HttpSession session,
			Model model
			) throws ControllerException {
		log.trace("get() invoked.");
		
		try {
			//Step.1 : Session으로부터 유저 정보 획득하여 Criteria에 저장
			//------------------------------------------------
			// 세션객체로부터, 회원정보 얻기
			LoginVO vo = (LoginVO) session.getAttribute(SharedScopeKeys.USER_KEY);
			
			int user_no = vo.getUser_no();	// 임시코드 : 1번 User NO조회
			//------------------------------------------------
			
			List<GroupChatVO> list = new ArrayList<>();
			
			list = this.groupChatService.getMyGroupList(user_no);
			
			model.addAttribute("groups", list);
			
			return "/chat/group_chat";

		} catch (Exception e) {
			throw new ControllerException(e);
		}

	} // get
	
	


} // end class
