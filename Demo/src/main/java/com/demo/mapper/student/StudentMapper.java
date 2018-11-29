package com.demo.mapper.student;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.demo.pojo.Student;

@Mapper
public interface StudentMapper {
	
	@Delete("delete from student where id =#{id}")
    int deleteByPrimaryKey(Integer id);
    
    int insert(Student record);
    
    @Insert("insert into student(name,age,sex,city_id) values(#{name},#{age},#{sex},#{city_id})")
    int insertSelective(Student record);
    
    @Select("select * from student where id=#{id}")
    Student selectByPrimaryKey(Integer id);
    
    @Update("update student SET name=#{name},age=#{age},sex=#{sex},city_id=#{city_id} WHERE id =#{id}")
    int updateByPrimaryKeySelective(Student record);
    
    int updateByPrimaryKey(Student record);
    
    //@Select("select s.id,s.name,s.age,s.sex,c.city_name from student.student s left join springbootdb.city c on s.city_id=c.id")
    @Select("select * from student")
    List<Student> listAll();
    
}