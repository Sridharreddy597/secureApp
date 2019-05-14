package com.example.secureApp.aop;

import org.aspectj.lang.annotation.Pointcut;

public class CommonJointPointConfig {

	@Pointcut("execution(* com.example.secureApp.controller.HomeController.getHomeUrl())") // point-cut expression
	public void logTimeForService() {
		
	}
	
}
