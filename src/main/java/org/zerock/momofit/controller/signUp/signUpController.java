package org.zerock.momofit.controller.signUp;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.zerock.momofit.exception.ControllerException;
import org.zerock.momofit.exception.ServiceException;
import org.zerock.momofit.service.signUpCheck.idAndPw.signUpCheckService;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@AllArgsConstructor

@RequestMapping("/signup")
@Controller
public class signUpController {
	
	private signUpCheckService idCheckService;
	
	@GetMapping
	public String signUp() {
		log.trace("signUp() invoked.");
		
		return "signUp/signup";
		
	} // signUp
	
	@PostMapping
	public String signUpProcess() {
		log.trace("signUpProcess() invoked.");
		
		return "signUp/signup";
		
	} // signUpProcess
	
	@GetMapping("/map")
	public String location() {
		log.trace("location() invoked.");
		
		return "signUp/map";
		
	} // location
	
	@ResponseBody
	@PostMapping("/idCheck")
	public boolean idCheck(String id) throws ControllerException {
		log.trace("idCheck({}) invoked.", id);
		
		try {
			boolean idResult = this.idCheckService.idCheck(id);
			return idResult;
			
		} catch (Exception e) {
			throw new ControllerException(e);
		} // try-catch
	

	} // idCheck
	
	@ResponseBody
	@PostMapping("/nickNameCheck")
	public boolean nickNameCheck(String nickname) throws ControllerException {
		log.trace("nickNameCheck({}) invoked.", nickname);
		
		try {
			boolean nickNameresult = this.idCheckService.nickNameCheck(nickname);
			return nickNameresult;
			
		} catch (Exception e) {
			throw new ControllerException(e);
		} // try-catch
	

	} // idCheck
		
} // end class
