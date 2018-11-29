package com.hello.mapper.city;

import com.hello.pojo.City;

public interface CityMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(City record);

    int insertSelective(City record);

    City selectByPrimaryKey(int id);

    int updateByPrimaryKeySelective(City record);

    int updateByPrimaryKey(City record);
}