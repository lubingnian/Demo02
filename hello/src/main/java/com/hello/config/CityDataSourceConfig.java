package com.hello.config;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import com.alibaba.druid.pool.DruidDataSource;

@ConfigurationProperties(prefix = "spring.datasource.city")
@Configuration
@MapperScan(basePackages=CityDataSourceConfig.PACKAGE,sqlSessionFactoryRef="citySqlSessionFactory")
public class CityDataSourceConfig {
	
	//city dao 所在的包
    public static final String PACKAGE = "com.hello.mapper.city";

    //mapper所在目录
    private static final String MAPPER_LOCATION = "classpath:mapper/city/*.xml";
	
	@Value("${spring.datasource.city.driverClassName}")
    private String driverClassName;

    @Value("${spring.datasource.city.url}")
    private String url;

    @Value("${spring.datasource.city.username}")
    private String username;

    @Value("${spring.datasource.city.password}")
    private String password;
	
	//初始化数据库连接
    @Bean(name="cityDataSource")
    public DataSource setDataSource(){
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setDriverClassName(driverClassName);
        dataSource.setUrl(url);
        dataSource.setUsername(username);
        dataSource.setPassword(password);
        return dataSource;
    }
	
	// 创建数据源的事务管理
	@Bean(name = "cityTransactionManager")
	public DataSourceTransactionManager setTransactionManager() {
		return new DataSourceTransactionManager(setDataSource());
	}
	
	@Bean(name = "citySqlSessionFactory")
    public SqlSessionFactory setSqlSessionFactory(@Qualifier("cityDataSource") DataSource dataSource) throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        bean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources(MAPPER_LOCATION));
        return bean.getObject();
    }
}
