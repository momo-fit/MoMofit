package org.zerock.momofit.interceptor.login;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.util.WebUtils;
import org.zerock.momofit.common.SharedScopeKeys;
import org.zerock.momofit.domain.signIn.LoginVO;
import org.zerock.momofit.service.signIn.LoginService;

import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;

@Log4j2
@NoArgsConstructor

@Component
public class AuthInterceptor implements HandlerInterceptor {
	
	@Setter(onMethod_ = {@Autowired})
	private LoginService service;	
	
	// 자동로그인 처리된 유저라면 쿠키 부활처리
	@Override
	public boolean preHandle(HttpServletRequest req, HttpServletResponse res, Object handler) throws Exception {
		log.trace("1. preHandle(req, res, {}) invoked.", handler);
		
		HttpSession session = req.getSession();		
		
		// Scope에 REMEMBERME_KEY 있는지 확인
		LoginVO vo = (LoginVO) session.getAttribute(SharedScopeKeys.USER_KEY);
		log.info("vo  :{}", vo);
		
		if(vo == null) {
			// 자동로그인 쿠키가 들어온 웹 브라우저라면 이 쿠키값으로 table 을 조회
			// 그 결과 user 획득, 이 정보로 인증객체 Session Scope에 바인딩
			Cookie rememberMeCookie = WebUtils.getCookie(req, SharedScopeKeys.REMEMBERME_KEY);
			
			if(rememberMeCookie != null) {
				String CookieValue = rememberMeCookie.getValue();
	
				LoginVO repairedUserVO = this.service.seleteRememberMeCookie(CookieValue);
				
				session.setAttribute(SharedScopeKeys.USER_KEY, repairedUserVO);
				
				// 컨트롤러 위임 X
				if(repairedUserVO != null) return true; 
				
			} // if		
			res.sendRedirect("/signin");
			
			return false;
			
		} else { return true; }// else-if
					
	} // preHandle
	
} // end class
