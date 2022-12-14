package org.zerock.momofit.controller.main;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.zerock.momofit.domain.group.GroupVO;
import org.zerock.momofit.domain.main.BoardVO;
import org.zerock.momofit.exception.ControllerException;
import org.zerock.momofit.service.mainPage.mainPageService;

import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;

@Log4j2
@NoArgsConstructor

@RequestMapping("/")
@Controller
public class mainPageController {
	
	@Setter(onMethod_ = {@Autowired})
	private mainPageService service; 
	
	@GetMapping
	public String mainPage(Model model) throws ControllerException {
		log.trace("mainPage() invoked.");
		
		try {
			List<GroupVO> BestGroupList = this.service.selectBestGroups();
			List<GroupVO> NewGroupList = this.service.selectNewGroups();
			List<BoardVO> BestBoard = this.service.selectBestBoard();
			
			List<BoardVO> BestFreeBoard = new ArrayList<>();
			List<BoardVO> BestTipBoard = new ArrayList<>();
			List<BoardVO> BestPicBoard = new ArrayList<>();
			List<BoardVO> BestTradeBoard = new ArrayList<>();
			
			for(BoardVO vo : BestBoard) {
				
				switch (vo.getCategory_no()) {
				case 1:
					BestFreeBoard.add(vo);	
					break;
				case 2:
					BestTipBoard.add(vo);
					break;
				case 3:
					BestPicBoard.add(vo);
					break;
				case 4:
					BestTradeBoard.add(vo);
					break;

				default:
					break;
				} // switch
	
			} // for
			
			model.addAttribute("BestGroupList",BestGroupList);
			model.addAttribute("NewGroupList", NewGroupList);
			
			model.addAttribute("BestFreeBoard",BestFreeBoard);
			model.addAttribute("BestTipBoard", BestTipBoard);
			model.addAttribute("BestPicBoard",BestPicBoard);
			model.addAttribute("BestTradeBoard", BestTradeBoard);
	
			return "main/main";
		}catch (Exception e) {
			throw new ControllerException(e);
		} // try-catch
		
	} // mainPage
	
} // end class
