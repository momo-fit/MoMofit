package org.zerock.momofit.controller.board;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.zerock.momofit.domain.picboard.PicBoardCommDTO;
import org.zerock.momofit.domain.picboard.PicBoardDTO;
import org.zerock.momofit.exception.ControllerException;
import org.zerock.momofit.service.picboard.PicBoardCommService;

import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;

@NoArgsConstructor
@Log4j2

@RequestMapping("/board/pic")
@Controller
public class PicCommController {
	
	@Setter(onMethod_= {@Autowired})
	private PicBoardCommService PicBoardCommservice;

	
	
	@PostMapping("/registerComm")
	public String registerComm(PicBoardDTO dto,PicBoardCommDTO commDto,RedirectAttributes rttrs)throws ControllerException {
		log.trace("register invoke");
		
		try {
			boolean isRegisterComm=this.PicBoardCommservice.registerComm(commDto);
			log.info("\t+isRegister",isRegisterComm);
			
			 rttrs.addAttribute("result", isRegisterComm ? "댓글이 작성되었습니다." : "댓글이 작성되지않았습니다.");
			
			 return "redirect:/board/pic/view?board_no="+commDto.getBoard_no();
			// return "redirect:/board/pic/view?board_no="+dto.getBoard_no();
			 
		}catch(Exception e) {
			throw new ControllerException(e);
		}
			
	}//댓글 작성 처리 
	

		
	
	
	
	

}
