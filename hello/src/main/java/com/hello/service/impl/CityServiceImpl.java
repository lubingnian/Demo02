package com.hello.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hello.mapper.city.CityMapper;
import com.hello.pojo.City;
import com.hello.service.CityService;

@Service("cityService")
public class CityServiceImpl implements CityService{
	
	@Autowired
	private CityMapper mapper;
	
	@Override
	public City get(int id) {
		return mapper.selectByPrimaryKey(id);
	}
	
}
