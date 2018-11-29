package com.demo.service;

import java.util.List;

import com.demo.pojo.Student;

public interface StudentService {
	List<Student> listAll();
	void add(Student student);
	void update(Student student);
	void delete(int id);
}
