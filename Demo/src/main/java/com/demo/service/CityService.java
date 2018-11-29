package com.demo.service;

import java.util.List;

import com.demo.pojo.City;

public interface CityService {
	City get(int id);
	
	void insert(City city);
	
	void update(City city);
	
	void delete(int id);
	
	List<City> listAll();
}
