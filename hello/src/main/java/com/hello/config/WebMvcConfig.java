package com.hello.config;

import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * WebMvcConfigurerAdapter（spring boot2.0以后过期会出现划线 ）可改为implements
 * WebMvcConfigurer 配置viewController,为页面提供路径映射
 */
@Controller
public class WebMvcConfig implements WebMvcConfigurer {

	/**
	 * 配置viewController,提供映射路径
	 */
	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addViewController("/webSocket").setViewName("/webSocket");
		registry.addViewController("/login").setViewName("/login");
        registry.addViewController("/chat").setViewName("/chat");
	}
}
