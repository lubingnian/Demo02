package com.demo.mapper.city;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.demo.pojo.City;

@Mapper
public interface CityMapper {
	
	@Delete("DELETE FROM city WHERE id =#{id}")
	int deleteByPrimaryKey(Integer id);
    
    int insert(City record);
    
    @Insert("insert into city(name) values(#{name})")
    int insertSelective(City record);
    
    @Select("select * from city where id=#{id}")
    @Results({
        @Result(property = "cityName", column = "city_name")
    })
    City selectByPrimaryKey(int id);
    
    @Update("UPDATE city SET name=#{name} WHERE id =#{id}")
    int updateByPrimaryKeySelective(City record);

    int updateByPrimaryKey(City record);
    
    @Select("select * from city")
    List<City> listAll();
}