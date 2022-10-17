package org.zerock.momofit.controller.center;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.zerock.momofit.domain.qnaboard.QnaBoardDTO;
import org.zerock.momofit.domain.qnaboard.Criteria;
import org.zerock.momofit.domain.qnaboard.QnaBoardVO;
import org.zerock.momofit.exception.ControllerException;
import org.zerock.momofit.exception.ServiceException;
import org.zerock.momofit.service.qnaboard.QnaBoardService;

import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;

@NoArgsConstructor
@Log4j2

@RequestMapping("/center/qna")
@Controller


public class QnaController {
	
	@Setter(onMethod_= {@Autowired})
	private QnaBoardService QnaBoardService;
	
	@GetMapping("/list")
	public String list(Model model,Criteria cri)throws ControllerException {
		try {
			
			List<QnaBoardVO>list= this.QnaBoardService.getList(cri);
			model.addAttribute("QnaBoardList", list);
		log.trace("list invoke");
		return "/center/qna/Inquiry_board";
		}catch (Exception e) {
			throw new ControllerException(e);}
	}//list 목록
	
	
	@GetMapping("/view")
	public String view() {
		log.trace("view invoke");
		return "/center/qna/main_board_qna";
		
	}//view 상세글조회
	
		
	@GetMapping("/modify")
	public String modifyForm() {
		log.trace("modify invoke");
		return "/center/qna/modify";
		
	}//modify수정화면 띄우기
	
	@PostMapping("/modify")
	public String modify() {
		log.trace("modify invoke");
		return "redirect:/center/qna/list";
	}//수정처리(처리 후 리스트로 이동)
	
	
	@GetMapping("/register")
	public String registerForm() {
		log.trace("registerForm invoke");
		return "/center/qna/writing";
		
	}//작성화면
		
	@PostMapping("/register")
	public String register(QnaBoardDTO dto,RedirectAttributes rttrs) throws ControllerException{
		
		log.trace("register invoke");
		try {
			boolean isRegister=this.QnaBoardService.register(dto);
			rttrs.addAttribute("result", isRegister ? "게시글이 작성되었습니다." : "게시글이 작성되지않았습니다.");	
		return "redirect:/center/qna/list";
		}catch(Exception e) {
			throw new ControllerException(e);
		}
	}//작성처리(처리후 리스트로 이동)
	
	@PostMapping("/remove")
	public String remove() {
		return "redirect:/center/qna/list";
		
	}//remove삭제
	

	

	
	
	

}
