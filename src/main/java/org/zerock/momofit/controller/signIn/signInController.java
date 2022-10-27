package org.zerock.momofit.controller.signIn;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.zerock.momofit.common.SharedScopeKeys;
import org.zerock.momofit.domain.signIn.LoginDTO;
import org.zerock.momofit.domain.signIn.LoginVO;
import org.zerock.momofit.exception.ControllerException;
import org.zerock.momofit.service.signIn.LoginService;

import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;

@Log4j2
@NoArgsConstructor

@Controller
@RequestMapping("/signin")
public class signInController {
	
	@Setter(onMethod_ = {@Autowired})
	private LoginService service;
	
	private String referer;
	
	@GetMapping
	public String signin(HttpServletRequest req, Model model) {
		log.trace("signin() invoked.");

		this.referer = req.getHeader("Referer");
		log.info("\t+ this.referer: {}", this.referer);
		
		
		return "signIn/login";
	} // signin
	

	@PostMapping
	public String signinProcess(LoginDTO dto, Model model, RedirectAttributes rttrs) throws ControllerException {
		log.trace("signinProcess() invoked.");
		
		try {
			
			LoginVO vo = this.service.userLoginService(dto);	

			if(vo != null) {
				// 성공 -> Session Scope model add
				model.addAttribute(SharedScopeKeys.LOGIN_KEY, vo);
				model.addAttribute(SharedScopeKeys.REFERER_URL, referer);
				
				return "/signIn/loginPost";
			} else {
				// 실패
				rttrs.addFlashAttribute("loginFailResult", "아이디 또는 비밀번호가 일치하지 않습니다.");
				return "redirect:/signin";
			} // if else
			
		} catch (Exception e) {
			throw new ControllerException(e);
		} // try - catch
		
	} // end signinProcess
	
	
	@GetMapping("/signout")
	public void signout() {
		log.trace("signout() invoked.");

	} // logout
	
} // end class
