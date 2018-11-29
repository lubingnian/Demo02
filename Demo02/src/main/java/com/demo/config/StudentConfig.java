package com.demo.config;

import java.io.IOException;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

@Configuration // 表示为一个springIOC容器
// 指定扫描mapper的路径，指明该接口注入此实例，管理MyBatis的SqlSession,调用MyBatis的SQL方法
@MapperScan(basePackages="com.demo.dao.student", sqlSessionTemplateRef="studentSqlSessionTemplate")
public class StudentConfig {
	
	@Bean //自动装配一个Bean，交由spring管理
	@Primary // 声明默认使用
	@ConfigurationProperties(prefix="spring.datasource.student") // 查找配置文件中相应的配置
	public DataSource studentDataSource() {
		return DataSourceBuilder.create().build();
	}
	
	// 创建数据源的事务管理 在业务逻辑类中需要事务管理的方法上加注解@Transactional当前使用事务
	@Bean
	public DataSourceTransactionManager stydentTransactionManager() {
		return new DataSourceTransactionManager(studentDataSource());
	}

	
	@Bean 
	public SqlSessionFactory studentSqlSessionFactory(@Qualifier("studentDataSource") DataSource dataSource) throws IOException {
		SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
		bean.setDataSource(dataSource);
		ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
		try {
			bean.setMapperLocations(resolver.getResources("classpath*:com/demo/dao/student/*.xml"));
			return bean.getObject();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@Bean
	public SqlSessionTemplate studentSqlSessionTemplate(@Qualifier("studentSqlSessionFactory") SqlSessionFactory sqlSessionFactory) {
		SqlSessionTemplate template = new SqlSessionTemplate(sqlSessionFactory);
		return template;
	}
	
}
