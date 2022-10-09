package org.zerock.momofit.controller.mypage;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.zerock.momofit.common.SharedScopeKeys;
import org.zerock.momofit.domain.mypage.Criteria;
import org.zerock.momofit.domain.mypage.MyCommentVO;
import org.zerock.momofit.domain.mypage.PageDTO;
import org.zerock.momofit.domain.signIn.LoginVO;
import org.zerock.momofit.domain.signUp.UserDTO;
import org.zerock.momofit.exception.ControllerException;
import org.zerock.momofit.service.mypage.MyCommentService;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@AllArgsConstructor

@RestController
@RequestMapping("/mypage/comment/")
public class MyCommentRestController {

	
	private MyCommentService myCommentService;
	
	@GetMapping(
			value="/categorys/{category}/pages/{page}",
			consumes = "application/json",
			
			produces = {
					MediaType.APPLICATION_JSON_VALUE
					}
			)
	public ResponseEntity<Map<String, Object>> getMyPageCommentList(
				@PathVariable("category") int category,
				@PathVariable("page") int page,
				Criteria cri,
				HttpSession session
			) throws ControllerException{
		log.trace("getMyPageCommentList({}, {}, {}) invoked.", category, page, cri);
		
		try {
			//Step.1 : Session으로부터 유저 정보 획득하여 Criteria에 저장
			//------------------------------------------------
			// 세션객체로부터, 회원정보 얻기
			LoginVO vo = (LoginVO) session.getAttribute(SharedScopeKeys.USER_KEY);
			
			int user_no = vo.getUser_no();	// 임시코드 : 1번 User NO조회
			//------------------------------------------------
			
			cri.setUser_no(user_no);
			
			// Step.2 : ajax로 Client에서 전송받은 변수 바인딩
			cri.setCurrPage(page);
			cri.setCategory(category);
			
			// Step.3 : Service계층으로부터, 내 글 획득
			List<MyCommentVO> voList = this.myCommentService.getCommentList(cri);
			
			// Step.4 : 페이징 처리를 위한 PageDTO 객체 생성
			int totalAmount = this.myCommentService.getCommentCount(cri);
			log.info("\t+ totalAmount : {}", totalAmount);
			
			PageDTO pageDTO = new PageDTO(cri, totalAmount);
			
			// Step.5 : Response Body에 넣어줄 Map 객체 생성
			Map<String, Object> result = new HashMap<>();
			
			result.put("voList", voList);
			result.put("pageDTO", pageDTO);
			
			return new ResponseEntity<>(result, HttpStatus.OK);
			
		} catch (Exception e) {
			throw new ControllerException(e);
		} // try-catch
		
	} // getMyPageCommentList
	
	
	@DeleteMapping(
			value = "/replys/{rno}",
			produces = MediaType.TEXT_PLAIN_VALUE
			)
	public ResponseEntity<String> removeMyPageReply(
			@PathVariable("rno") int comm_no
			) throws ControllerException {
		log.trace("removeMyPageReply({}) invoked.", comm_no);
		
		try {
			
			boolean result = this.myCommentService.removeReply(comm_no);
			
			return new ResponseEntity<>(result ? String.valueOf(comm_no) : "", HttpStatus.OK);
			
		} catch (Exception e) {
			throw new ControllerException(e);
		} // try-catch
		
	} // removeMyPageReply
	
} // end class
