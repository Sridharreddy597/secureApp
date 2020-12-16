package com.example.secureApp;

import javax.servlet.Filter;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import com.example.secureApp.aop.LoggerAspect;
import com.example.secureApp.auth.SecurityConfig;
import com.example.secureApp.config.PersistenceConfig;
import com.example.secureApp.config.webMvcConfig;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = { "com.example.secureApp" })
public class SecureAppServletInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

	@Override
	protected Class<?>[] getRootConfigClasses() {
		// TODO Auto-generated method stub
		return new Class[] { SecureAppServletInitializer.class, LoggerAspect.class, SecurityConfig.class,
				PersistenceConfig.class };
	}
	//test jenkins

	@Override
	protected Class<?>[] getServletConfigClasses() {
		// TODO Auto-generated method stub
		return new Class[] { webMvcConfig.class };
	}

	@Override
	protected String[] getServletMappings() {
		// TODO Auto-generated method stub
		return new String[] { "/*" };
	}

	@Override
	protected Filter[] getServletFilters() {
		return super.getServletFilters();
	}

}