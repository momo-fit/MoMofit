package org.zerock.momofit.controller.group;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.zerock.momofit.domain.group.Criteria;
import org.zerock.momofit.domain.group.GroupDTO;
import org.zerock.momofit.domain.group.GroupVO;
import org.zerock.momofit.domain.group.PageDTO;
import org.zerock.momofit.exception.ControllerException;
import org.zerock.momofit.service.group.GroupService;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@AllArgsConstructor

@Controller
@RequestMapping("/group/")
public class GroupController {
	
	private GroupService service;
	
	
//	// 1. 전체목록 조회하기
//	
//	@GetMapping("/list")
//	public void list(Model model) throws ServiceException {
//		log.trace("list() invoked.");
//		
//		model.addAttribute("list", service.getList());
//				
//	} // list
	
	// 1-2. 페이징된 전체목록 조회
	@GetMapping("/list")
	public void listPerPage(Criteria cri, Model model) throws ControllerException {
		log.trace("listPerPage() invoked.");
		
		try {
			List<GroupVO> list = this.service.getListPerPage(cri);
			
			model.addAttribute("list", list);	
			
			PageDTO pageDTO = new PageDTO(cri, this.service.getTotal());
			model.addAttribute("pageMaker", pageDTO);
			
		} catch(Exception e) {
			throw new ControllerException(e);
		} // try-catch
	} // list
	
	
	// 2-1. 모임 작성 (입력 화면)

	@GetMapping("/register")
	public void write(@ModelAttribute("cri") Criteria cri, Model model) throws ControllerException {
		log.trace("write({})ivoked.", cri);
		
				
	} // write
	
	
	// 2-2. 모임 등록
	@PostMapping("/register")
	public String register(GroupDTO dto, RedirectAttributes rttr, Criteria cri, String sightingDateString) throws ControllerException {
		
		try {
				
			boolean isRegister = service.register(dto);
						
			rttr.addFlashAttribute("result", (isRegister)? "SUCCESS("+ dto.getGroup_no()+")" : "FAILURE");
			
			rttr.addAttribute("currPage", cri.getCurrPage());
			
			return "redirect:/group/list";
			

						
		} catch(Exception e) {
			throw new ControllerException(e);
		}

		
	} // register
	
	// 모임 등록 과정에서, <input type=datetime-local> 태그로 선택한 모임일정이 Date타입으로 변환되지 않는 이슈 발생
	/* 에러메세지: Failed to convert property value of type 'java.lang.String' 
	 			   to required type 'java.util.Date' for property 'dateExpire';   */
	
	// 백엔드 Date 필드에 수신 날짜 문자열을 처리하는 중 오류 -> Controller에 InitBinder를 추가
	@InitBinder
    protected void init(HttpServletRequest request, ServletRequestDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
        dateFormat.setLenient(false);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
    }
	
	
	// 3. 모임 상세보기
	
	@GetMapping("/detail")
	public void detail(
			// @RequestParam : 매개변수와 전송파라미터의 이름과 달라도 얻고싶을 때 사용
			// @ModelAttribute : 정말 model에 담겼는지 불확실하다면 사용
			
			@RequestParam("group_no") Integer group_no, @ModelAttribute("cri") Criteria cri, Model model
			) throws ControllerException {
		
			log.trace("detail({}, {}) invoked.", group_no, cri);
			
			try {	
				GroupDTO dto = new GroupDTO();
				
				model.addAttribute("group", service.detail(group_no));
				
			} catch(Exception e) {
				throw new ControllerException(e);
			}
	}
	
	// 4-1. 모임 수정 (입력 화면)
	
	@GetMapping("/modify")
	public void modify(@RequestParam("group_no") Integer group_no, @ModelAttribute("cri") Criteria cri, Model model) 
			throws ControllerException {
		log.trace("modify() invoked.");
		
		try {
			model.addAttribute("group", service.detail(group_no));
		
		} catch(Exception e) {
			throw new ControllerException(e);
		}
	} // modify
	
	// 4-2. 모임 수정
	
	@PostMapping("/modify")
	public String update(GroupDTO dto, @ModelAttribute("cri") Criteria cri, RedirectAttributes rttr) 
			throws ControllerException {
		log.trace("modify() invoked.");
		
		try {
			if (service.modify(dto)) {
				rttr.addFlashAttribute("result", "success");
			}		
			rttr.addAttribute("currPage", cri.getCurrPage());
		
			return "redirect:/group/list";
			
		} catch(Exception e) {
			throw new ControllerException(e);
		}
		
	} // update
	

	// 5. 모임 삭제
	
	@PostMapping("/remove")
	public String remove(@RequestParam("group_no") Integer group_no, @ModelAttribute("cri") Criteria cri, RedirectAttributes rttr) 
			throws ControllerException {
		log.trace("remove() invoked.");
		
		try {
//			model.addAttribute("group", service.remove(group_no));
			if (service.remove(group_no)) {
				rttr.addAttribute("result", "success");
			}
			
			rttr.addAttribute("currPage", cri.getCurrPage());
				
			return "redirect:/group/list";
			
		} catch(Exception e) {
			throw new ControllerException(e);
		}
					
	} // remove

	
	// 6-1. 모임참가자 목록 (입력 화면)
	@GetMapping("/memberList")
	public String memberList() {
		log.trace("memberList() invoked.");
		
		return "/group/memberList";
	} // delegate
	
	// 6-2. 모임장 위임
	@PostMapping("/delegate")
	public String delegate() {
		log.trace("delegate() invoked.");
		
		return "redirect:/group/detail";
	} // delegate
	
	// 7. 모임채팅
	@GetMapping("/chatting")
	public String chatting() {
		log.trace("chatting() invoked.");
		
		return "/group/chat";
	}
	

} // class
