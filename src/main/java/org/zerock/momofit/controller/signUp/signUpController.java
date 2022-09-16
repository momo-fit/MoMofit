package org.zerock.momofit.controller.signUp;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.zerock.momofit.domain.signUp.UserDTO;
import org.zerock.momofit.exception.ControllerException;
import org.zerock.momofit.service.signUp.userSignUpService;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@AllArgsConstructor

@RequestMapping("/signup")
@Controller
public class signUpController {

	private userSignUpService userSignUpService;
	
	@GetMapping
	public String signUp() {
		log.trace("signUp() invoked.");
		
		return "signUp/signup";
		
	} // signUp
	
	@PostMapping
	public String signUpProcess(UserDTO dto, RedirectAttributes rttrs) throws ControllerException {
		log.trace("signUpProcess() invoked.");
		
		try {
			boolean result = this.userSignUpService.UserSignUp(dto);

			rttrs.addFlashAttribute("result", (result)? "회원가입에 성공했습니다" : "회원가입에 실패했습니다." );
			
			return "redirect:/signin";
			
		}catch (Exception e) {
			throw new ControllerException(e);
		} // try-catch	
		
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
			boolean idResult = this.userSignUpService.idCheck(id);
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
			boolean nickNameresult = this.userSignUpService.nickNameCheck(nickname);
			return nickNameresult;
			
		} catch (Exception e) {
			throw new ControllerException(e);
		} // try-catch
	

	} // idCheck
		
} // end class
