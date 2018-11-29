package com.hello.config;
import javax.servlet.Filter;

/*
 * 这个类中声明了一个@Bean ，用于生成一个过滤器类，对/secure 链接下的所有资源访问进行JWT的验证
 */
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.hello.filter.JwtFilter;

@Configuration
public class JwtConfig {
	
	@Bean
	public FilterRegistrationBean<Filter> jwtFilter() {
		final FilterRegistrationBean<Filter> registrationBean = new FilterRegistrationBean<>();
		registrationBean.setFilter(new JwtFilter());
		registrationBean.addUrlPatterns("/user/*");
		System.out.println("JWT-过滤");
		return registrationBean;
	}
}
