package com.demo.dao.city;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import com.demo.pojo.city.Test;

public interface TestMapper {
	
	@Select("select * from test where id=#{id}")
	Test get (int id);
	
	@Insert("insert into test(name) values(#{name})")
	void add(String name);
}
