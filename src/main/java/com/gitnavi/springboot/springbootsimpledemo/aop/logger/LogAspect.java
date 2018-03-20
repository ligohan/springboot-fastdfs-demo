package com.gitnavi.springboot.springbootsimpledemo.aop.logger;

import com.gitnavi.springboot.springbootsimpledemo.common.GlobalVariable;
import lombok.extern.log4j.Log4j2;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

@Aspect
@Order(5)
@Component
@Log4j2
public class LogAspect {


	//=================================================================================

	private long startTime;

	@Pointcut("execution(public * com.gitnavi.springboot.springbootsimpledemo..controller..*.*(..))")
	public void logPointcut() {
	}

	//=================================================================================

	@AfterThrowing(pointcut = "logPointcut()", throwing = "e")
	public void logAfterThrowing(JoinPoint joinPoint, Throwable e) {
		log.trace("----------------AOP Log logAfterThrowing----------------Exception in {}.{}() with cause = {}",
				joinPoint.getSignature().getDeclaringTypeName(),
				joinPoint.getSignature().getName(),
				e.getCause() != null ? e.getCause() : "NULL");
	}

	//=================================================================================

	@Before("logPointcut()")
	public void doBefore(JoinPoint joinPoint) throws Throwable {
		startTime = System.currentTimeMillis();

		// 接收到请求，记录请求内容
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();

		// 记录下请求内容
		log.trace("----------------AOP Log doBefore----------------request url: <{}>", request.getRequestURL().toString());
		log.trace("----------------AOP Log doBefore----------------request http_method: <{}>", request.getMethod());
		log.trace("----------------AOP Log doBefore----------------request ip: <{}>", request.getRemoteAddr());
		log.trace("----------------AOP Log doBefore----------------request class_method: <{}>", joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName());
		log.trace("----------------AOP Log doBefore----------------request args: <{}>", Arrays.toString(joinPoint.getArgs()));

	}

	//=================================================================================

	@AfterReturning(returning = "ret", pointcut = "logPointcut()")
	public void doAfterReturning(Object ret) throws Throwable {
		// 处理完请求，返回内容
		log.trace("----------------AOP Log doAfterReturning----------------response result: <{}>", ret);
	}

	//=================================================================================

	@After("logPointcut()")
	public void doAfter() throws Throwable {
		long diffTime = (System.currentTimeMillis() - startTime) / 1000;
		log.trace("----------------AOP Log doAfter----------------spent time: <{}> 秒", diffTime);

		if (diffTime > GlobalVariable.SERIOUS_PERFORMANCE_PROBLEMS_TIME_THRESHOLD) {
			log.warn("----------------AOP Log doAfter----------------严重注意：该方法可能存在严重性能问题");
		} else if (diffTime > GlobalVariable.GENERAL_PERFORMANCE_PROBLEMS_TIME_THRESHOLD) {
			log.warn("----------------AOP Log doAfter----------------注意：该方法可能存在一般性能问题");
		} else if (diffTime > GlobalVariable.NEED_OPTIMIZE_TIME_THRESHOLD) {
			log.warn("----------------AOP Log doAfter----------------提示：检查该方法是否有优化的空间");
		}
	}


}

