package com.demo.pojo.city;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class Test {
	@NotNull
	private int id;
	@NotEmpty(message="姓名不能为空！")
	private String name;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
}
