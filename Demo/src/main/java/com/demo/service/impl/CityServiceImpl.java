package com.demo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.mapper.city.CityMapper;
import com.demo.pojo.City;
import com.demo.service.CityService;

@Service("cityService")
public class CityServiceImpl implements CityService{
	
	@Autowired
	private CityMapper mapper;
	
	@Override
	public City get(int id) {
		return mapper.selectByPrimaryKey(id);
	}

	@Override
	public void insert(City city) {
		mapper.insertSelective(city);
	}

	@Override
	public void update(City city) {
		mapper.updateByPrimaryKeySelective(city);
	}

	@Override
	public void delete(int id) {
		mapper.deleteByPrimaryKey(id);
	}

	@Override
	public List<City> listAll() {
		return mapper.listAll();
	}
	
}
