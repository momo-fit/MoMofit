package org.zerock.momofit.controller.mypage;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.NoArgsConstructor;


//-- View 화면 보이기 위한 Controller
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
	
	
	

} // end class
