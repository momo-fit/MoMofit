package org.zerock.momofit.controller.mypage;

import java.security.KeyPair;
import java.security.PrivateKey;
import java.security.PublicKey;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.zerock.momofit.common.SharedScopeKeys;
import org.zerock.momofit.domain.signIn.LoginVO;
import org.zerock.momofit.domain.signUp.UserDTO;
import org.zerock.momofit.exception.ControllerException;
import org.zerock.momofit.service.mypage.MyInfoModifyService;
import org.zerock.momofit.util.RSAEncryptionUtil;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@AllArgsConstructor

@RequestMapping("/mypage/")
@Controller
public class MyInfoModifyController {
	
	private MyInfoModifyService myInfoModifyService;
	
	// 1. mypage "패스워드 체크"
	// Server측 RSA 공개키-개인키 생성
	@GetMapping("/check_pw")
	public void check_pw (
			HttpSession session,
			Model model
			) throws ControllerException {
		log.trace("check_pw() invoked.");
		
		try {
			// Step.1 : RSA KeyPair 생성
			KeyPair keyPair = RSAEncryptionUtil.createRSAKeyPair();
			
			// Step.2 : 개인키 -> SessionScope 저장
			PrivateKey privateKey = keyPair.getPrivate();
			PublicKey publicKey = keyPair.getPublic();
			
			session.setAttribute(SharedScopeKeys.PRIVATE_KEY, privateKey);
			
			// Step.3 : 공개키 -> Base64인코딩 -> RequestScope저장
			String base64PublicKey = RSAEncryptionUtil
					.base64EncoderToString(publicKey);

			model.addAttribute(SharedScopeKeys.PUBLIC_KEY, base64PublicKey);

		} catch (Exception e) {
			throw new ControllerException(e);
		} // try-catch
		

	} // check_pw
	
	@PostMapping("/check_pw")
	public String check_pw_post(
			String password,
			HttpSession session,
			RedirectAttributes rttrs
			) throws ControllerException {
		log.trace("check_pw_post() invoked.");
		log.info("\t+ password : {}", password);
		
		try {
			//Step.0 : Session으로부터 유저 정보 획득하여 Criteria에 저장
			//------------------------------------------------
			// 세션객체로부터, 회원정보 얻기
			LoginVO vo = (LoginVO) session.getAttribute(SharedScopeKeys.USER_KEY);
			
			int user_no = vo.getUser_no();	// 임시코드 : 1번 User NO조회
			//------------------------------------------------
			
			
			// Step.1 : 개인키(Server)
			PrivateKey privateKey = 
					(PrivateKey)session.getAttribute(SharedScopeKeys.PRIVATE_KEY);
		
			// Step.2 : 복호화 진행
			String decrypted = RSAEncryptionUtil.decrypt(password, privateKey);
			log.info("\t+ decrypted : {}", decrypted);
			
			// -- 사용한 PrivateKey는 삭제
			session.removeAttribute(SharedScopeKeys.PRIVATE_KEY);
			
			// Step.3 : Pass유효성 검사
			boolean result = this.myInfoModifyService.checkValidPass(decrypted, user_no);
			log.info("\t+ result : {}", result);
			
			if(result) {
				
				session.setAttribute("SUCCESS_CHECKPASS", "SUCCESS");
				
				return "redirect:/mypage/info_modify";
				
			} else {
				rttrs.addFlashAttribute(SharedScopeKeys.ERROR_MESSAGE, "패스워드가 일치하지 않습니다.");
				
				return "redirect:/mypage/check_pw";
				
			} // if-else
			
			
		// RSA 복호화 실패 예외처리
		} catch (IllegalArgumentException e) {
			rttrs.addFlashAttribute(SharedScopeKeys.ERROR_MESSAGE, "오류가 발생하였습니다. 다시 시도해주십시오.");
			return "redirect:/mypage/check_pw";
		} catch (Exception e) {			
			throw new ControllerException(e);		
		} // try-catch
		

	} // check_pw_post
	
	
	@GetMapping("/info_modify")
	public String infoModify(
			Model model,
			HttpSession session
			) throws ControllerException {
		log.trace("infoModify() invoked.");
		
		try {
			String result = (String)session.getAttribute("SUCCESS_CHECKPASS");		
	
			
			if(result != null && !result.isBlank()) {
				log.trace("RSAKeyGenerator invoked.");
				KeyPair keyPair = RSAEncryptionUtil.createRSAKeyPair();
				
				PrivateKey privateKey = keyPair.getPrivate();
				PublicKey publicKey = keyPair.getPublic();
				
				String base64PublicKey = RSAEncryptionUtil.base64EncoderToString(publicKey);
				
				session.setAttribute(SharedScopeKeys.PUBLIC_KEY, base64PublicKey);
				session.setAttribute(SharedScopeKeys.PRIVATE_KEY, privateKey);
				
				return "/mypage/info_modify";
			} else {		
				return "redirect: /mypage/check_pw";
			} // if-else
		} catch (Exception e) {
			throw new ControllerException(e);
		}
	} // infoModify
	
	@PostMapping("/info_modify")
	public String infoUserModify(
			UserDTO dto,
			@RequestParam("file")MultipartFile file,
			HttpSession session,
			RedirectAttributes rttrs
			) throws ControllerException {
		log.trace("infoUserModify() invoked.");
		
		log.info("\t+ file : {}", file);
		
		try {
			// RSA 복호화 작업
			if(dto.getPass() != null && !dto.getPass().isBlank()) {
				
				PrivateKey privateKey = (PrivateKey)session.getAttribute(SharedScopeKeys.PRIVATE_KEY);
				String decrypted = RSAEncryptionUtil.decrypt(dto.getPass(), privateKey);
				
				// -- 사용한 PrivateKey는 삭제
				session.removeAttribute(SharedScopeKeys.PRIVATE_KEY);
				
				dto.setPass(decrypted);
				
			} // if
			
			
			
			// SessionScope에서 기존User정보 획득
			LoginVO vo = (LoginVO)session.getAttribute(SharedScopeKeys.USER_KEY);
			dto.setUser_no(vo.getUser_no());
			// 유저정보 업데이트
			boolean updateResult = this.myInfoModifyService.modifyUserInfo(dto, vo, file);
			
			if(updateResult) {			
				// 업데이트 된 새로운 유저정보 회득 -> Session Scope 업데이트
				LoginVO updateVO = this.myInfoModifyService.getUserInfo(vo.getUser_no());
				session.setAttribute(SharedScopeKeys.USER_KEY, updateVO);
				
				return "redirect:/mypage/my_group";
			} else {
				rttrs.addFlashAttribute(SharedScopeKeys.ERROR_MESSAGE, "오류가 발생하였습니다.");			
				return "redirect:/mypage/info_modify";
			} // if-else
			
			
		} catch (Exception e) {
			throw new ControllerException(e);
		}

	} // infoUserModify
	

} // end class
