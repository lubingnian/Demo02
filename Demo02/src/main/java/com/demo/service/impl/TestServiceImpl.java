package com.demo.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.demo.dao.city.TestMapper;
import com.demo.pojo.city.Test;
import com.demo.service.TestService;

@Service
public class TestServiceImpl implements TestService{
	
	@Autowired
	TestMapper mapper;
	
	@Override
	@Transactional(value="cityTransactionManager")// 指定使用该事务管理，在city数据源配置中配置的事务管理
	public void add(String name) {
		mapper.add("555");
		mapper.add("666");
		mapper.add("7777777");// 执行到此，会有数据库添加字段长度异常，数据库字段长度为5，事务执行回滚
		mapper.add("888");
	}

}
