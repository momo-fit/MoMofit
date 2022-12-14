package org.zerock.momofit.controller.mypage;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.zerock.momofit.common.SharedScopeKeys;
import org.zerock.momofit.domain.mypage.Criteria;
import org.zerock.momofit.domain.mypage.MyGroupVO;
import org.zerock.momofit.domain.mypage.PageDTO;
import org.zerock.momofit.domain.signIn.LoginVO;
import org.zerock.momofit.exception.ControllerException;
import org.zerock.momofit.service.mypage.MyGroupService;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;


@Log4j2
@RequiredArgsConstructor

@RestController
@RequestMapping("/mypage/group/")
public class MyGroupRestController {

	
	private final MyGroupService myGroupService;
	
	
	@GetMapping(
			value="/pages/{page}",
			produces = {
					MediaType.APPLICATION_JSON_VALUE
					}
			)
	public ResponseEntity<Map<String, Object>> getMyPageGroupList(
			@PathVariable("page") int page,
			HttpSession session
			) throws ControllerException {
		log.trace("getMyPageGroupList({}) invoked.", page);
		
		try {
			//Step.1 : Session으로부터 유저 정보 획득하여 Criteria에 저장
			//------------------------------------------------
			// 세션객체로부터, 회원정보 얻기
			LoginVO vo = (LoginVO) session.getAttribute(SharedScopeKeys.USER_KEY);		
			int user_no = vo.getUser_no();
			//------------------------------------------------
			
			Criteria cri = new Criteria();
			cri.setUser_no(user_no);
			
			
			// Step.2 : ajax로 Client에서 전송받은 객체 바인딩
			// (1) 현재페이지
			cri.setCurrPage(page);
			cri.setAmount(3);
			
			// Step.3 : Service계층에서, 내가 참여한 "그룹"목록 획득
			List<MyGroupVO> voList = this.myGroupService.getGroupList(cri);
			
			// Step.4 : 페이징 처리를 위한 PageDTO 객체 생성
			int totalAmount = this.myGroupService.getGroupCount(user_no);
			
			PageDTO pageDTO = new PageDTO(cri, totalAmount);
			
			// Step.5 : ResponseBody에 넣어줄 맵객체 생성
			Map<String, Object> result = new HashMap<>();
			
			result.put("voList", voList);
			result.put("pageDTO", pageDTO);
			log.info("result : {}, {}", result);
			
			return new ResponseEntity<>(result, HttpStatus.OK);
			
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		
	} // getMyPageGroupList
	

	@PutMapping(
			value="/groups/{group_no}/users/{user_no}",
			produces = {
					"application/text; charset=UTF-8"
					}
			)
	public ResponseEntity<String> quitMyGroup(
			@PathVariable("group_no") int group_no,
			@PathVariable("user_no") int user_no
			) throws ControllerException{
		log.trace("quitMyGroup({},{}) invoked.", group_no, user_no);
		
		try {
			
			// Step.1 : Quit이 가능한지 유효성 검사
			if(this.myGroupService.isQuitGroup(group_no, user_no)) {
				return new ResponseEntity<>("모임장은 나가기가 불가 합니다.", HttpStatus.OK);
			} // if : 유효성 검사
			log.info("그룹을 나가겠습니다.");
			
			// Step.2 : 모임에서 나가기
			return this.myGroupService.quitMyGroup(group_no, user_no)?
					new ResponseEntity<>("모임에서 나갔습니다.", HttpStatus.OK) :
						new ResponseEntity<>("나가기가 실패하였습니다.", HttpStatus.OK);
			
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	} // quitMyGroup

	
} // end class
