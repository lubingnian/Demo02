package com.demo;

import java.security.Key;
import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.dao.city.CityMapper;
import com.demo.dao.city.TestMapper;
import com.demo.dao.student.StudentMapper;
import com.demo.pojo.city.Test;
import com.demo.service.TestService;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

@RestController
@SpringBootApplication
public class Demo02Application {

	@Autowired
	StudentMapper studentMapper;
	@Autowired
	CityMapper cityMapper;
	@Autowired
	TestMapper testMapper;

	@RequestMapping("/get")
	public Map get(@Valid Test test,BindingResult bindingResult) throws Exception {
		Map map = new HashMap<>();
		if(bindingResult.hasErrors()){
			 map.put("err",bindingResult.getFieldError().getDefaultMessage());
		}else{
			map.put("3", testMapper.get(test.getId()));
		}
		//map.put("1", studentMapper.get(1));
		//map.put("2", cityMapper.get(1));
		return map;
	}
	
	@RequestMapping("getJws")
	public String getJws() {
		Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256);
		String jws = Jwts.builder().setSubject("Joe").signWith(key).compact();
		//Jwts.parser().setSigningKey(key).parseClaimsJws(jws).getBody().getSubject().equals("Joe");
		try {
			Jwts.parser().setSigningKey(key).parseClaimsJws("4545454");
		} catch (JwtException e) {
			return "no";
		}
		return jws;
	}

	public static void main(String[] args) {
		SpringApplication.run(Demo02Application.class, args);
	}
}
