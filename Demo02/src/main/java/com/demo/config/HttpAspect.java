package com.demo.config;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Aspect // 表明为切面类
@Component // 声明为一个组件，交由spring管理
public class HttpAspect {
	
	//Spring自带的日志框架 slf4j
	private final static Logger logger = LoggerFactory.getLogger(HttpAspect.class);
	
	// 定义切入点
	@Pointcut("execution(public * com.demo.*.get(..))")
	public void log(){
		
	}
	
	// 后置通知
	@After("log()")
	public void doAfter(){
		logger.info("Aspect:after:log");
	}
	
	// 前置通知
	@Before("log()")
	public void doBefore(JoinPoint joinPoint){
		//请求的url
		ServletRequestAttributes attributes =  (ServletRequestAttributes)RequestContextHolder.getRequestAttributes();
		HttpServletRequest request = attributes.getRequest();
		logger.info("url={}",request.getRequestURL());
		//请求的method
		logger.info("method={}",request.getMethod());
		//请求的ip
		logger.info("ip={}",request.getRemoteAddr());
		//请求的方法
		logger.info("class_method={}",joinPoint.getSignature().getDeclaringTypeName()+"."+joinPoint.getSignature().getName());
		//请求的参数
		logger.info("参数={}",joinPoint.getArgs());
	}
	
	// 后置返回
	@AfterReturning(returning = "object" , pointcut="log()")
	public void doAfterReturning(Object object){
		logger.info("返回对象={}",object);
	}
	
}
