package org.zerock.momofit.interceptor.login;

import java.sql.Timestamp;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.zerock.momofit.common.SharedScopeKeys;
import org.zerock.momofit.domain.signIn.LoginVO;
import org.zerock.momofit.service.signIn.LoginService;

import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;


@Log4j2
@NoArgsConstructor

@Component
public class LoginInterceptor implements HandlerInterceptor {
	
	@Setter(onMethod_ = {@Autowired})
	private LoginService service;
	
	@Override
	public boolean preHandle(HttpServletRequest req, HttpServletResponse res, Object handler) throws Exception {
		log.trace("preHandle(req, res, {}) invoked.", handler);
		
		// 접근 가능 session 획득
		HttpSession session = req.getSession();
		
		// vo 인증객체 삭제 ( 로그인 클릭시 기존에 있던 객체 삭제 )
		session.removeAttribute(SharedScopeKeys.USER_KEY);
		
		return true;
		
	} // preHandle

	@Override
	public void postHandle(HttpServletRequest req, HttpServletResponse res, Object handler, ModelAndView modelAndView) throws Exception {
		log.trace("postHandle(req,res, {}, {}) invoked.", handler, modelAndView);
		
		HttpSession session = req.getSession();
		
		// Controller 에서 addAttribute 한 값 받기
		ModelMap model = modelAndView.getModelMap();
		LoginVO vo = (LoginVO) model.get(SharedScopeKeys.LOGIN_KEY);
		log.info("\t+ VO : {}", vo);
		
		// 이전 url 가져오기
		String url = (String) model.getAttribute(SharedScopeKeys.REFERER_URL);
		
		if(vo != null) {
			// 로그인 성공
			
			session.setAttribute(SharedScopeKeys.USER_KEY, vo);
			
			boolean isRememberMe = checkRememberMeOption(req);
			log.info("\t+ rememberMe : {}", isRememberMe);
						
			if(isRememberMe) {
				// 쿠키 가져오기
				String sessionId = req.getSession().getId();
				Cookie rememberMeCookie = new Cookie(SharedScopeKeys.REMEMBERME_KEY, sessionId);
				
				// 1주 보관
				final int mexAge = 60 * 60 * 24 * 7;
				rememberMeCookie.setMaxAge(mexAge);
				rememberMeCookie.setPath(sessionId);
				
				res.addCookie(rememberMeCookie);
				
				// 쿠키 값 + 만료일자 DB 저장
				long now = System.currentTimeMillis();
				long expiry = now + (mexAge * 1000L);
				
				Timestamp expiryTs = new Timestamp(expiry);
				log.info("\t+ rememberMe : {}", isRememberMe);
				
				boolean isModifyUserWithRememberMe = this.service.modifyUserRememberMe(vo.getId(), sessionId, expiryTs);
				log.info("\t+ 1. isModifyUserWithRememberMe: {}", isModifyUserWithRememberMe);
				
			} // if : isRememberMe
			
			if(!url.contains("/find/id") && !url.contains("/find/pw") && !url.contains("/signup")) {
				res.sendRedirect(url);
				model.remove(SharedScopeKeys.REFERER_URL);
			} // if : contains

		} // if : vo != null
				
	} // postHandle
	
	private boolean checkRememberMeOption(HttpServletRequest req) {
		log.trace("checkRememberMeOption() incoked");
		
		String rememberMe = req.getParameter("rememberMe");
		
		return rememberMe != null;
	} // checkRememberMeOption
	
	
} // end class
