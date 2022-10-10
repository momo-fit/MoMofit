package org.zerock.momofit.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import lombok.extern.log4j.Log4j2;

@Log4j2

@Aspect
@Component
public class TimeTraceAop {

//	@Around("execution(* org.zerock.momofit..*(..))")
//	public Object execute(ProceedingJoinPoint joinPoint) throws Throwable {
//		long start = System.currentTimeMillis();
//		log.trace("-------------START : {}", joinPoint.toString());
//		
//		try {
//			return joinPoint.proceed();
//		} finally {
//			long finish = System.currentTimeMillis();
//			long timeMs = finish - start;
//			
//			log.trace("-------------END : {} {}ms", joinPoint.toString(), timeMs);		
//		}
//		
//	} // execute
	
} // end class
