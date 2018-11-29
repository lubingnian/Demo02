package com.hello.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hello.mapper.city.CityMapper;
import com.hello.mapper.student.StudentMapper;
import com.hello.pojo.City;
import com.hello.pojo.Student;
import com.hello.service.StudentService;

@Service("studentService")
public class StudentServiceImpl implements StudentService{
	
	@Autowired
	StudentMapper mapper;
	
	@Autowired
	CityMapper mapper2;
	
	@Override
	public List<Student> listAll() {
		return mapper.listAll();
//		List<Student> list = new ArrayList<>();
//		Student student = mapper.selectByPrimaryKey(1);
//		City city = mapper2.selectByPrimaryKey(1);
//		student.setName(city.getCityName());
//		list.add(student);
//		return list;
	}

	@Override
	public void add(Student student) {
		mapper.insertSelective(student);
	}

	@Override
	public void update(Student student) {
		mapper.updateByPrimaryKeySelective(student);
	}

	@Override
	public void delete(int id) {
		mapper.deleteByPrimaryKey(id);
	}

	@Override
	public Student selectByName(String name) {
		return mapper.selectByName(name);
	}
	
}
