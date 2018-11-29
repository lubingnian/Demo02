package com.hello.service;

import java.util.List;

import com.hello.pojo.Student;

public interface StudentService {
	List<Student> listAll();
	void add(Student student);
	void update(Student student);
	void delete(int id);
	Student selectByName(String name);
}
