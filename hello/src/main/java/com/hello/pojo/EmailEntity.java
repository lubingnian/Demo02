package com.hello.pojo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "smtp")
public class EmailEntity  {
	 //此处填写SMTP服务器
    private String service;
    //设置端口号
    private String port;
    //设置发送邮箱
    private String address;
    // 设置发送邮箱的STMP口令
    private String pwd;
    //设置邮件标题
    private String title;
    //设置邮件内容
    private String content;
    //内容格式（默认采用html）
    private String contentType;
    private String  nickname;
    //接受邮件地址集合
    private List<String> list = new ArrayList<>();
	public String getService() {
		return service;
	}
	public void setService(String service) {
		this.service = service;
	}
	public String getPort() {
		return port;
	}
	public void setPort(String port) {
		this.port = port;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getContentType() {
		return contentType;
	}
	public void setContentType(String contentType) {
		this.contentType = contentType;
	}
	public List<String> getList() {
		return list;
	}
	public void setList(List<String> list) {
		this.list = list;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
    
}
