package org.zerock.momofit.controller.mypage;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;


//-- 임시 컨트롤러입니다. View 화면 보이기 위한 Controller이므로, 최종본이 아님.
@Log4j2
@NoArgsConstructor

@RequestMapping("/mypage/")
@Controller
public class MyPageController {
	
	
//	===========================
	
	// 1. mypage "내 글" 이동
	@GetMapping("/board_list")
	public void myboardList() {
	} // myboardList	
	
	// 2. mypage "내 댓글" 이동
	@GetMapping("/comment_list")
	public void commentList() {
	} // commentList	
	
	// 3. mypage "내 문의/신고" 이동
	@GetMapping("/csboard_list")
	public void csboardList() {
	} // csboardList
	
	// 4. mypage "내 그룹" 이동
	@GetMapping("/my_group")
	public void myGroupList() {
	} // myGroupList
	
	// 6. mypage "내 쪽지"
	@GetMapping("/message_receive")
	public void noteReceiveList() {
	} // noteReceiveList
	
	

} // end class
