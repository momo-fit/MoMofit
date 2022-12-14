package org.zerock.momofit.controller.center;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.zerock.momofit.common.SharedScopeKeys;
import org.zerock.momofit.domain.qnaboard.Criteria;
import org.zerock.momofit.domain.qnaboard.PageDTO;
import org.zerock.momofit.domain.qnaboard.QnaBoardDTO;
import org.zerock.momofit.domain.qnaboard.QnaBoardVO;
import org.zerock.momofit.domain.qnaboard.qna_imgDTO;
import org.zerock.momofit.domain.signIn.LoginVO;
import org.zerock.momofit.exception.ControllerException;
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
	HttpSession session;
	
	@GetMapping("/list")
	public String list(Model model,Criteria cri)throws ControllerException {
		try {
			
			List<QnaBoardVO>list= this.QnaBoardService.getList(cri);
			model.addAttribute("QnaBoardList", list);
			
			PageDTO pageDTO = new PageDTO(cri,this.QnaBoardService.getTotal(cri));
 			
			model.addAttribute("pageMaker", pageDTO);
			
		log.trace("list invoke");
		return "/center/qna/Inquiry_board";
		}catch (Exception e) {
			throw new ControllerException(e);}
	}//list 목록
	
	
	@GetMapping("/view")
	public String view(QnaBoardDTO dto,qna_imgDTO imageDto,
			@ModelAttribute("cri")Criteria cri,
			Model model)throws ControllerException  {
		
		try {
			QnaBoardVO vo=this.QnaBoardService.get(dto);
			 
	         log.info("\t+vo:{}", vo);
	        
	          model.addAttribute("QnaBoard", vo);
			log.trace("view invoke");
			return "/center/qna/main_board_qna";
			}catch(Exception e) {
				throw new ControllerException(e);
			}
		
	}//view 상세글조회
	
		
	@GetMapping("/modify")
	public String modify(
			QnaBoardDTO dto,
			@ModelAttribute("cri")Criteria cri,
			Model model) 
			throws ControllerException{
		try {

			QnaBoardVO vo=this.QnaBoardService.get(dto);
	         log.info("\t+vo:{}", vo);
	         
	         model.addAttribute("QnaBoard", vo);
		return "/center/qna/modify";
		}catch(Exception e) {
			throw new ControllerException(e);
		}
		
	}//modify수정화면 띄우기
	
	@PostMapping("/modify")
	public String modify(QnaBoardDTO dto,@ModelAttribute("cri")Criteria cri,RedirectAttributes rttrs)throws ControllerException {
		log.trace("modify invoke");
		try {
			boolean isModify=this.QnaBoardService.modify(dto);
	         log.info("\t+ isModify", isModify);

	         rttrs.addAttribute("result", isModify ? "게시글이 수정되었습니다.(" + dto.getQna_no() + ")" : "게시글 수정에 실패했습니다.");
	         rttrs.addAttribute("currPage", cri.getCurrPage());
		return "redirect:/center/qna/list";
		}catch(Exception e) {
			throw new ControllerException(e);
		}
	}//수정처리(처리 후 리스트로 이동)
	
	
	@GetMapping("/register")
	public String registerForm() {
		log.trace("registerForm invoke");
		return "/center/qna/writing";
		
	}//작성화면
		
	@PostMapping("/register")
	public String register(QnaBoardDTO dto,RedirectAttributes rttrs,HttpSession session) throws ControllerException{
		
		log.trace("register invoke");
		try {
			LoginVO loginVO = (LoginVO) session.getAttribute(SharedScopeKeys.USER_KEY);
            
            int user_no = loginVO.getUser_no();
            
			boolean isRegister=this.QnaBoardService.register(dto);
			rttrs.addAttribute("result", isRegister ? "게시글이 작성되었습니다." : "게시글이 작성되지않았습니다.");	
		return "redirect:/center/qna/list";
		}catch(Exception e) {
			throw new ControllerException(e);
		}
	}//작성처리(처리후 리스트로 이동)
	
	@PostMapping("/remove")
	public String remove(QnaBoardDTO dto,@ModelAttribute("cri")Criteria cri,RedirectAttributes rttrs) throws ControllerException {
		
		try {
			boolean isRemove=this.QnaBoardService.remove(dto);
	         log.info("\t+ isRemove", isRemove);
	         
	         
	         rttrs.addAttribute("result", isRemove ? "게시글이 삭제되었습니다.(" + dto.getQna_no() + ")" : "게시글 삭제가 실패하였습니다.");
	         rttrs.addAttribute("currPage", cri.getCurrPage());
		return "redirect:/center/qna/list";
		} catch (Exception e) {
			throw new ControllerException(e);
		}
		
	}//remove삭제
	

	

	
	
	

}
