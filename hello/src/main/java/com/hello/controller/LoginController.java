package com.hello.controller;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.hello.pojo.Student;
import com.hello.service.StudentService;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@RestController
@RequestMapping(value = "/user")
public class LoginController {

	@Autowired
	private StudentService studentService;

	@RequestMapping("/login")
	public ModelAndView login() {
		ModelAndView model = new ModelAndView();
		model.setViewName("login2");
		return model;
	}

	@ResponseBody
	@RequestMapping(value = "/loginCheck", method = RequestMethod.POST)
	public String loginCheck(Student student,HttpServletRequest request) {
		String authHeader = request.getHeader("authorization");
		System.out.println(authHeader);
		// 登录成功
		boolean flag = true;
		String result = "登录成功";
		// 根据用户名查询用户是否存在
		Student studentEntity = studentService.selectByName(student.getName());
		// 用户不存在
		if (studentEntity == null) {
			flag = false;
			result = "用户不存在，登录失败";
		}
		// 密码错误
		else if (!studentEntity.getAge().equals(student.getAge())) {
			flag = false;
			result = "用户密码不相符，登录失败";
		}
		// 登录成功
		if (flag) {
			result = Jwts.builder().setSubject(student.getName()).claim("roles", "member").setIssuedAt(new Date())
					.signWith(SignatureAlgorithm.HS256, "secretkey").compact();
			// 将用户写入session
			// request.getSession().setAttribute("_session_student",studentEntity);
		}
		return result;
	}
}