package org.zerock.momofit.controller.board;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.zerock.momofit.domain.picboard.Criteria;
import org.zerock.momofit.domain.picboard.PageDTO;
import org.zerock.momofit.domain.picboard.PicBoardDTO;
import org.zerock.momofit.domain.picboard.PicBoardVO;
import org.zerock.momofit.domain.picboard.board_imgDTO;
import org.zerock.momofit.exception.ControllerException;
import org.zerock.momofit.service.picboard.PicBoardService;


import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;

@NoArgsConstructor
@Log4j2

@RequestMapping("/board/pic")
@Controller
public class PicController {
	
	@Setter(onMethod_= {@Autowired})
	private PicBoardService PicBoardservice;

	

	@GetMapping({"/view"})  
	public String view(
//			Integer board_no,
			PicBoardDTO dto,board_imgDTO imageDto,
			@ModelAttribute("cri")Criteria cri,
			Model model) 
					throws ControllerException {
		log.trace("view({},{}) invoke",dto,cri);
		
		try {
//			PicBoardDTO dto= new PicBoardDTO();
//			dto.setBoard_no(board_no);
			PicBoardVO vo=this.PicBoardservice.get(dto);
			 
	         log.info("\t+vo:{}", vo);
	        
	          model.addAttribute("PicBoard", vo);  //여기까지는 상세조회에 대한 코드
	          
	          return "/board/pic/main_board_pic";
			}catch(Exception e) {
				throw new ControllerException(e);
			}
			
		
	}//view 상세글조회
	
	@GetMapping({"/modify"})  
	public String modify(
//			Integer board_no,
			PicBoardDTO dto,
			@ModelAttribute("cri")Criteria cri,
			Model model) 
					throws ControllerException {
		log.trace("view({},{}) invoke",dto,cri);
		
		try {
//			PicBoardDTO dto= new PicBoardDTO();
//			dto.setBoard_no(board_no);
			
			PicBoardVO vo=this.PicBoardservice.get(dto);
	         log.info("\t+vo:{}", vo);
	         
	         model.addAttribute("PicBoard", vo);
	         
			 return "/board/pic/modify";
		}catch(Exception e) {
			throw   new ControllerException(e);
		}
		
		
	}//수정화면(get맵핑)

	
	@PostMapping("/modify")
	public String modify(PicBoardDTO dto,@ModelAttribute("cri")Criteria cri,RedirectAttributes rttrs)throws ControllerException  {
		log.trace("modify invoke");
		
		try {
			boolean isModify=this.PicBoardservice.modify(dto);
	         log.info("\t+ isModify", isModify);

	         rttrs.addAttribute("result", isModify ? "게시글이 수정되었습니다.(" + dto.getBoard_no() + ")" : "게시글 수정에 실패했습니다.");
	         rttrs.addAttribute("currPage", cri.getCurrPage());
			return "redirect:/board/pic/list";
		}catch(Exception e) {
			throw new ControllerException(e);
		}
	}//수정처리(처리 후 리스트로 이동)
	
	
	@GetMapping("/register")
	public String registerForm() {
		log.trace("registerForm invoke");
		return "/board/pic/writing";
	 	
	}//작성화면
		
	@PostMapping("/register")
	public String register(PicBoardDTO dto,RedirectAttributes rttrs)throws ControllerException {
		log.trace("register invoke");
		
		try {
			boolean isRegister=this.PicBoardservice.register(dto);
			log.info("\t+isRegister",isRegister);
			
			 rttrs.addAttribute("result", isRegister ? "게시글이 작성되었습니다." : "게시글이 작성되지않았습니다.");
			
			 return "redirect:/board/pic/list";
			 
			 
		}catch(Exception e) {
			throw new ControllerException(e);
		}
			
	}//작성처리(처리후 리스트로 이동)
	
	
	
	@PostMapping("/remove")
	public String remove(PicBoardDTO dto,@ModelAttribute("cri")Criteria cri,RedirectAttributes rttrs) throws ControllerException {
		
		try {
			boolean isRemove=this.PicBoardservice.remove(dto);
	         log.info("\t+ isRemove", isRemove);
	         
	         
	         rttrs.addAttribute("result", isRemove ? "게시글이 삭제되었습니다.(" + dto.getBoard_no() + ")" : "게시글 삭제가 실패하였습니다.");
	         rttrs.addAttribute("currPage", cri.getCurrPage());
	         return "redirect:/board/pic/list";
		} catch (Exception e) {
			throw new ControllerException(e);
		}
		
	}//remove삭제	
	
	@GetMapping("/list")
	public String listPerPage(Model model,Criteria cri) throws ControllerException {
		log.trace("listPerPage() invoke");
		try {
			List<PicBoardVO> list = this.PicBoardservice.getListWithPaging(cri);
			
			model.addAttribute("PicBoardList", list);
			
			PageDTO pageDTO = new PageDTO(cri,this.PicBoardservice.getTotal(cri)); //매개변수에 cri넣어줌 (검색하려고)
 			
			model.addAttribute("pageMaker", pageDTO);
			
			return "/board/pic/sports_check";
		} catch (Exception e) {
			
			throw new ControllerException(e);
			
		}
	}//list 목록 (페이징처리)
	
	

		
	
	
	
	

}
