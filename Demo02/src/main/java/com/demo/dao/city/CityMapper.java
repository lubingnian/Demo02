package com.demo.dao.city;

import org.apache.ibatis.annotations.Select;

import com.demo.pojo.city.City;

public interface CityMapper {
	@Select("select * from city where id=#{id}")
	City get(int id);
}
