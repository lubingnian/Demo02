package com.hello.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * Spring Security配置
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
            .antMatchers("/", "login").permitAll() //设置Spring Security对/和"/login"路径不拦截
            .anyRequest().authenticated()
            .and().formLogin().loginPage("/login")//设置Spring Security的登陆页面访问路径为login
            .defaultSuccessUrl("/chat") //登陆成功后转向/chat路径
            .permitAll().and().logout()
            .permitAll();
    }
    /**
    *       .withUser("james").password("james").roles("user")
    *       .and()
    *       .withUser("curry").password("curry").roles("user");
    *        在security 5.0以后密码会进行加密访问会报错There is no PasswordEncoder mapped for the id “null”
    *        则需要改成：
    *       .passwordEncoder(new BCryptPasswordEncoder()).withUser("james").password(new BCryptPasswordEncoder().encode("123456")).roles("USER")
    *       .and()
    *       .passwordEncoder(new BCryptPasswordEncoder()).withUser("curry").password(new BCryptPasswordEncoder().encode("123456")).roles("USER");
    *
    *
    **/
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        //配置两个用户,角色是user
        auth.inMemoryAuthentication()
        .passwordEncoder(new BCryptPasswordEncoder()).withUser("james").password(new BCryptPasswordEncoder().encode("123456")).roles("USER")
            .and()
            .passwordEncoder(new BCryptPasswordEncoder()).withUser("curry").password(new BCryptPasswordEncoder().encode("123456")).roles("USER");
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        // 设置Spring Secutiry不拦截/resources/static/目录下的静态资源
        web.ignoring().antMatchers("/resources/static/**");
    }

}