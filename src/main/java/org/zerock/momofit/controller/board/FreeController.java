package org.zerock.momofit.controller.board;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.zerock.momofit.domain.freeboard.Criteria;
import org.zerock.momofit.domain.freeboard.FreeBoardDTO;
import org.zerock.momofit.domain.freeboard.FreeBoardVO;
import org.zerock.momofit.domain.freeboard.PageDTO;
import org.zerock.momofit.exception.ControllerException;
import org.zerock.momofit.service.freeboard.FreeBoardService;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@AllArgsConstructor // 자동주입

@RequestMapping("/board/free/")
@Controller
public class FreeController {
	
	// 자동주입	
	private FreeBoardService service;
	
	@GetMapping("/list")
	public String list(Criteria cri, Model model) throws ControllerException {
		log.info("list() invoked.");
		
		try {
			List<FreeBoardVO> list = this.service.getListPerPage(cri);
			model.addAttribute("list", list);
			
			PageDTO pageDTO = new PageDTO(cri, this.service.getTotal());
			model.addAttribute("pageMaker", pageDTO);
			
			return "/board/free/free_board";			
		} catch (Exception e) {
			throw new ControllerException(e);
		} // try-catch	
		
	} // list
	
	
	@GetMapping("/view")
	public String view(FreeBoardDTO dto, @ModelAttribute("cri") Criteria cri, Model model) throws ControllerException {
		log.info("view({}) invoked.", dto);
		
		try {
			FreeBoardVO vo = this.service.get(dto);
			log.info("\t+ vo : {}", vo);					
			model.addAttribute("freeboard", vo);
			
			return "/board/free/main_board_freeboard";			
		} catch (Exception e) {
			throw new ControllerException(e);
		} // try-catch
		
	} // view
	
	
	@PostMapping("/modify")                  // 수정화면에서 수정 완료 클릭시 리다이렉트 시키는 PostMapping
	public String modify(FreeBoardDTO dto, Criteria cri, RedirectAttributes rttrs) throws ControllerException {
		log.info("modify() invoked.");
		
		try {
			boolean isModify = this.service.modify(dto);
			log.info("\t+ isModify : {}", isModify);
			
			rttrs.addFlashAttribute("result", (isModify)?  "게시글 수정에 성공하였습니다." : "게시글 수정에 실패하였습니다." );
			rttrs.addAttribute("currPage", cri.getCurrPage());
			
			return "redirect:/board/free/list";  // 수정완료 했으니 목록으로 redirect
		} catch(Exception e) {
			throw new ControllerException(e);
		} // try-catch		
		
	} // modify
	
	
	@GetMapping("/modify")                   // View에서 수정버튼 눌러서 수정하는 화면으로 보내주는 GetMapping
	public String modifyMove(FreeBoardDTO dto, @ModelAttribute("cri") Criteria cri, Model model) throws ControllerException {
		log.info("modifyMove({}) invoked.", dto);
		
		try {
			FreeBoardVO vo = this.service.get(dto);
			log.info("\t+ vo : {}", vo);					
			model.addAttribute("freeboard", vo);
			
			return "/board/free/modify";
		} catch (Exception e) {
			throw new ControllerException(e);
		} // try-catch		
		
	} // modify
	
	
	@PostMapping("/register")                 // 글 작성 화면에서 작성완료 클릭시 리다이렉트 시키는 PostMapping
	public String register(FreeBoardDTO dto, RedirectAttributes rttrs) throws ControllerException {
		log.info("register() invoked.");
		
		try {
			boolean isRegister = this.service.register(dto);
			log.info("\t+ isRegister : {}", isRegister);
			
			rttrs.addFlashAttribute("result", (isRegister)? "게시글 작성에 성공하였습니다." : "게시글 작성에 실패하였습니다." );
			
			return "redirect:/board/free/list";  // 작성완료 했으니 목록으로 redirect			
		} catch(Exception e) {
			throw new ControllerException(e);
		} // try-catch
		
	} // register
	
	
	@GetMapping("/register")                  // List에서 글쓰기 버튼 눌러서 글 작성 화면으로 보내주는 GetMapping
	public String registerMove() {
		log.info("registerMove() invoked.");
		
		// 여기서 회원정보가 필요함 (회원제 글쓰기 게시판이기 때문에 회원번호를 글쓸 때 가지고 있어야함)
		
		return "/board/free/writing";
	} // register
	
	
	@PostMapping("/remove")
	public String remove(FreeBoardDTO dto, Criteria cri, RedirectAttributes rttrs) throws ControllerException {
		log.info("remove() invoked.");
		
		try {
			boolean isRemove = this.service.remove(dto);
			log.info("\t+ isRemove : {}", isRemove);						
			
			rttrs.addFlashAttribute("result", (isRemove)?  "게시글 삭제에 성공하였습니다." : "게시글 삭제에 실패하였습니다." );
			rttrs.addAttribute("currPage", cri.getCurrPage());
			
			return "redirect:/board/free/list";  // 삭제완료 했으니 목록으로 redirect
		} catch(Exception e) {
			throw new ControllerException(e);
		} // try-catch
		
	} // remove
	

	
} // end class
