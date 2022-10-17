package org.zerock.momofit.controller.find;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.zerock.momofit.exception.ControllerException;
import org.zerock.momofit.service.find.findService;

import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;

@Log4j2
@NoArgsConstructor

@RequestMapping("/find/")
@Controller
public class findIdPwController {
	
	@Setter(onMethod_ = {@Autowired})
	findService service;

	@GetMapping("/id")
	public String findId() {
		log.trace("findId() invoked.");
		
		return "/find/id_search";
	} // findId
	
	@GetMapping("/pw")
	public String findPw() {
		log.trace("findPw() invoked.");
		
		return "/find/pw_search";
	} // findPw
	
	@ResponseBody
	@PostMapping("/findId")
	public String findIdProcess(String email) throws ControllerException {
		log.trace("findIdProcess() invoked.");
		
		try {
			String resultId = this.service.selectFindId(email);
			
			return resultId;
		} catch (Exception e) {
			throw new ControllerException(e);
		}
		
	} // findIdProcess
		
	@ResponseBody
	@PostMapping("/findPW")
	public boolean findPwProcess(String email, String id) throws ControllerException {
		log.trace("findPwProcess() invoked.");
		
		try {
			boolean resultPw = this.service.selectFindPw(email , id);
			
			return resultPw;
		} catch (Exception e) {
			throw new ControllerException(e);
		}
		
	} // findPwProcess
		
	
} // end class
