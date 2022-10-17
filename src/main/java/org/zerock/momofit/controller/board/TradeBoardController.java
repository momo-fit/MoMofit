package org.zerock.momofit.controller.board;



import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.zerock.momofit.domain.tradeboard.Criteria;
import org.zerock.momofit.domain.tradeboard.PageDTO;
import org.zerock.momofit.domain.tradeboard.TradeBoardDTO;
import org.zerock.momofit.domain.tradeboard.TradeBoardVO;
import org.zerock.momofit.exception.ControllerException;
import org.zerock.momofit.service.tradeboard.TradeBoardService;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@AllArgsConstructor

@RequestMapping("/board/trade/")
@Controller
public class TradeBoardController {
	
	
	private TradeBoardService service;
	
	// 중고거래 게시판
	@GetMapping("/list")
	public void list(Criteria cri, Model model) throws ControllerException {
		log.trace("TradeBoardList() invoked.");
		
		try {

			List<TradeBoardVO> list = this.service.getListPerPage(cri);
			
			model.addAttribute("list", list);
			
			PageDTO pageDTO = new PageDTO(cri, this.service.getTotal());
			model.addAttribute("pageMaker", pageDTO);

		} catch(Exception e) {
			throw new ControllerException(e);
		} // try-catch
	} // listPerPage
	
	
	// 새로운 게시글 등록
	@PostMapping("/register")
	public String register(TradeBoardDTO dto, RedirectAttributes rttrs) throws ControllerException  {
		log.trace("register({}) invoked.", dto);
		try {
			boolean isRegister = this.service.register(dto);
			log.info("\t+ isRegister: {}", isRegister);
			
			rttrs.addAttribute("result",(isRegister)?"SUCCESS("+dto.getBoard_no()+")" : "FAILURE");
			
			return "redirect:/board/trade/list";	
		}catch(Exception e) {
			throw new ControllerException(e);
		} // try-catch

	} // register
	
	
	// servlet-context.xml view-controller로 대체? 는 안됨
	// 작성화면 이동
	@GetMapping("/register")
	public String registerMove() {
		log.info("registerMove() invoked.");
		

		return "/board/trade/writing";
	} // registerMove
	
	
	// 기존 게시글 수정
	@PostMapping("/modify")                  // 수정화면에서 수정 완료 클릭시 리다이렉트 시키는 PostMapping
	public String modify(TradeBoardDTO dto, Criteria cri, RedirectAttributes rttrs) throws ControllerException {
		log.info("modify() invoked.");
		
		try {
			boolean isModify = this.service.modify(dto);
			log.info("\t+ isModify: {}", isModify);
			
			rttrs.addAttribute("result", (isModify)? "성공!("+dto.getBoard_no()+")" : "실패");
			rttrs.addAttribute("currPage", cri.getCurrPage());
			
			return "redirect:/board/trade/list";  // 수정완료 했으니 목록으로 redirect
		}catch(Exception e) {
			throw new ControllerException(e);
		} // try-catch
	
	} // modify
	

	// 삭제 
	@PostMapping("/remove")
	public String remove(TradeBoardDTO dto, Criteria cri, RedirectAttributes rttrs) throws ControllerException {
		log.trace("remomve() invoked.");
		
		try {
			boolean isRemove = this.service.remove(dto);
			log.info("\t+ isRemove: {}", isRemove);
			
			rttrs.addAttribute("result", (isRemove)? "SUCCESS("+dto.getBoard_no()+")" : "FAILURE");
			rttrs.addAttribute("currPage", cri.getCurrPage());

			return "redirect:/board/trade/list";	
		}catch(Exception e) {
			throw new ControllerException(e);
		} // try-catch
	
	} // remove
		
		

	// 상세조회
//	@GetMapping("/view")
//	public String view() {
//		log.info("view() invoked.");
//		
//
//		return "/board/trade/main_board_trade";
//	} // view
	
	
//	@GetMapping("/view")
//	public String view(TradeBoardDTO dto, @ModelAttribute("cri") Criteria cri,
//			Model model) throws ControllerException{
//		
//		log.trace("view({},{}) invoked.",dto,cri);
//		
//		try {
//			TradeBoardVO vo = this.service.get(dto);
//			log.info("\t+ vo: {}", vo);
//			
//			model.addAttribute("TradeBoard", vo);
//			
//			return "/board/trade/main_board_trade";
//		} catch(Exception e) {
//			throw new ControllerException(e);
//		} // try-catch
//		
//	} // view
	

//	@GetMapping("/modify")
//	public String modifyMove(TradeBoardDTO dto, @ModelAttribute("cri") Criteria cri,
//			Model model) throws ControllerException {
//		log.info("modify({},{}) invoked.");
//		
//		try {
//			TradeBoardVO vo = this.service.get(dto);
//			log.info("\t+ vo: {}", vo);
//			
//			model.addAttribute("TradeBoard", vo);
//			
//			return "/board/trade/modify";
//		}catch(Exception e) {
//			throw new ControllerException(e);
//		}// try-catch
//		
//	} // modify
	
	
	@GetMapping({ "/view", "/modify" })
	public void view(
			TradeBoardDTO dto,
			@ModelAttribute("cri") Criteria cri,
			Model model
		) throws ControllerException {
		log.trace("get({}, {}) invoked.", dto, cri);
		
		try {
			TradeBoardVO vo = this.service.get(dto);
			log.info("\t+ vo: {}", vo);
			
			model.addAttribute("TradeBoard", vo);
		} catch(Exception e) {
			throw new ControllerException(e);
		} // try-catch
	} // get
	
	
	
	
} // end class
