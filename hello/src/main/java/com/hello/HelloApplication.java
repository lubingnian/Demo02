package com.hello;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hello.pojo.EmailEntity;
import com.hello.pojo.MailSender;
import com.hello.pojo.Student;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@RestController
@SpringBootApplication
@MapperScan("com.hello.mapper")
public class HelloApplication {
	
//	@Autowired
//	EmailEntity mail;
	
	@Autowired
    private JavaMailSender mailSender;
	
	@Autowired
	EmailEntity mail;
	
	@RequestMapping("/hello")
	public String hello() {
//		
//		mail.setContent("nihao");
//		MailSender send = new MailSender(mail);
//		List<String> list = new ArrayList<>();
//		list.add("1174758926@qq.com");
//		mail.setList(list);
//		mail.setTitle("ceshi");
//		try {
//			send.send();
//		} catch (UnsupportedEncodingException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		//List list = null;
//		Student student = new Student();
//		student.setId(1);
//		student.setAge(11);
//		student.setName("524");
		
		
		SimpleMailMessage message = new SimpleMailMessage();
		message.setFrom("1402659613@qq.com");// 发送者
		String[] a = {"1174758926@qq.com"};
		message.setTo(a);// 接收者.
		message.setSubject("测试邮件（邮件主题）");// 邮件主题.
		message.setText("这是邮件内容");// 邮件内容.
//		mailSender.send(message);// 发送邮件applicationContext
		
		
		
		// Create Twt token
//		  String jwtToken = Jwts.builder().setSubject("nihao")
//				    .claim("roles", "member").setIssuedAt(new Date())
//				    .signWith(SignatureAlgorithm.HS256, "secretkey").compact();
//		return jwtToken;
		return "yes";
	}
	
	public static void main(String[] args) {
		SpringApplication.run(HelloApplication.class, args);
	}
}