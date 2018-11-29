package com.hello.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.hello.pojo.Student;
import com.hello.service.StudentService;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/student")
public class StudentController {
	
    @Autowired
    StudentService studentService;

    @RequestMapping("/list")
    public ModelAndView listStudent(Student student){
        List<Student> students = studentService.listAll();
        ModelAndView model = new ModelAndView();
        model.addObject("students",students);
        model.setViewName("listStudent");
        return model;
    }
    
    @RequestMapping("/delete")
    public ModelAndView delete(int id){
    	studentService.delete(id);
        return listStudent(new Student());
    }
    
    @RequestMapping("/add")
    public ModelAndView add(Student student){
    	studentService.add(student);
        return listStudent(new Student());
    }
    
    @RequestMapping("/update")
    public ModelAndView update(Student student){
    	studentService.update(student);
        return listStudent(new Student());
    }
    
}
