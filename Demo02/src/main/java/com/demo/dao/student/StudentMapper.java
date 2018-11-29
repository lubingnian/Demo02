package com.demo.dao.student;

import org.apache.ibatis.annotations.Select;

import com.demo.pojo.student.Student;

public interface StudentMapper {
	@Select("select * from student where id=#{id}")
	Student get(int id);
}
