package org.zerock.momofit.interceptor.login;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.util.WebUtils;
import org.zerock.momofit.common.SharedScopeKeys;
import org.zerock.momofit.domain.signIn.LoginVO;

import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;

@NoArgsConstructor
@Log4j2

@Component
public class LogoutInterceptor implements HandlerInterceptor {

	@Override
	public boolean preHandle(HttpServletRequest req, HttpServletResponse res, Object handler) throws Exception {
		log.trace("1. preHandle(req, res, {}) invoked.", handler);
		
		HttpSession session = req.getSession();
		
		LoginVO vo = (LoginVO) session.getAttribute(SharedScopeKeys.USER_KEY);
		
		// session 파괴
		session.removeAttribute(SharedScopeKeys.USER_KEY);
		session.invalidate();
		
		log.info("\t+ session Destroyed : {}" ,vo);
		
		// 자동로그인 유효시 처리 로직
		Cookie rememberMeCookie = WebUtils.getCookie(req, SharedScopeKeys.REMEMBERME_KEY);
		
		// 삭제
		if(rememberMeCookie != null) {
			rememberMeCookie.setMaxAge(0);
			rememberMeCookie.setPath("/");
			
			res.addCookie(rememberMeCookie);
			log.info("\t+ rememberMeCookie");
		} // if
		
		// 로그아웃 성공 -> 페이지 이동
		req.getSession().setAttribute(SharedScopeKeys.LOGIN_RESULT, "로그아웃");
		res.sendRedirect("/");
		
		return true;
	} // preHandle

} // end class
