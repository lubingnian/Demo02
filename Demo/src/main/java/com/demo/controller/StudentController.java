package com.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.demo.mapper.student.StudentMapper;
import com.demo.pojo.City;
import com.demo.pojo.Student;
import com.demo.service.CityService;
import com.demo.service.StudentService;

import java.util.List;

@Controller
@RequestMapping("/student")
public class StudentController {
	
    @Autowired
    StudentService studentService;
    
    @Autowired
    CityService cityService;
    
    @Autowired
    StudentMapper mapper;

    @RequestMapping("/listStudent")
    @ResponseBody
    public ModelAndView listStudent(Student student){
        List<Student> students = mapper.listAll();
        List<City> citys = cityService.listAll();
        ModelAndView model = new ModelAndView();
        model.addObject("students",students);
        model.addObject("citys",citys);
        model.setViewName("index");
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
