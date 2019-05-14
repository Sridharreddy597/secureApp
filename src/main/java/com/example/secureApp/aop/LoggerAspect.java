package com.example.secureApp.aop;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Aspect
@Configuration
@EnableAspectJAutoProxy
public class LoggerAspect {

	private Logger logger = LogManager.getLogger(this.getClass());

	@Around("com.example.secureApp.aop.CommonJointPointConfig.logTimeForService()") // point-cut expression
	public void around(ProceedingJoinPoint joinPoint) throws Throwable {
		//Advice
		long startTime=System.currentTimeMillis();

		logger.info("execution start time for : "+joinPoint.getSignature().getName()+" is "+ System.currentTimeMillis());
		joinPoint.proceed();
		logger.info("execution end time for : "+joinPoint.getSignature().getName()+" is "+ System.currentTimeMillis());
		long timeTaken=System.currentTimeMillis()-startTime;
		logger.info("Time taken by "+joinPoint+" is(ms) : "+ timeTaken);
		
	}
}
