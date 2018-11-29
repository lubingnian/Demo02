package com.demo.config;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import com.alibaba.druid.pool.DruidDataSource;

@ConfigurationProperties(prefix = "spring.datasource.student")
@Configuration
@MapperScan(basePackages=StudentDataSourceConfig.PACKAGE,sqlSessionFactoryRef="studentSqlSessionFactory")
public class StudentDataSourceConfig {

	//student dao 所在的包
    public static final String PACKAGE = "com.demo.mapper.student";

    //mapper所在目录
    private static final String MAPPER_LOCATION = "classpath:mapper/student/*.xml";
	
	@Value("${spring.datasource.student.driverClassName}")
    private String driverClassName;

    @Value("${spring.datasource.student.url}")
    private String url;

    @Value("${spring.datasource.student.username}")
    private String username;

    @Value("${spring.datasource.student.password}")
    private String password;
	
	//初始化数据库连接
    @Bean(name="studentDataSource")
    @Primary
    public DataSource setDataSource(){
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setDriverClassName(driverClassName);
        dataSource.setUrl(url);
        dataSource.setUsername(username);
        dataSource.setPassword(password);
        return dataSource;
    }
	
	
	// 创建数据源的事务管理
	@Bean(name = "studentTransactionManager")
	@ConfigurationProperties(prefix="spring.datasource.student")
    @Primary
	public DataSourceTransactionManager setTransactionManager() {
		return new DataSourceTransactionManager(setDataSource());
	}
	
//	@Bean(name = "studentSqlSessionFactory")
//    @Primary
//    public SqlSessionFactory setSqlSessionFactory(@Qualifier("studentDataSource") DataSource dataSource) throws Exception {
//        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
//        bean.setDataSource(dataSource);
//        bean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources(MAPPER_LOCATION));
//        return bean.getObject();
//    }
	
	@Bean(name = "studentSqlSessionFactory")
	@Primary
    public SqlSessionFactory sqlSessionFactoryBean() throws Exception {

        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        org.apache.ibatis.session.Configuration ibatisConfiguration = new org.apache.ibatis.session.Configuration();
        ibatisConfiguration.setMapUnderscoreToCamelCase(true);
        sqlSessionFactoryBean.setConfiguration(ibatisConfiguration);
        sqlSessionFactoryBean.setDataSource(setDataSource());
        return sqlSessionFactoryBean.getObject();
    }
	
	
}
