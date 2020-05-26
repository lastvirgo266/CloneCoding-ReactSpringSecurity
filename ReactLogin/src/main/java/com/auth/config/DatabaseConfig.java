package com.auth.config;

import javax.sql.DataSource;


import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.util.Properties;

@Configuration
public class DatabaseConfig {
	
	
	@Bean
	@ConfigurationProperties(prefix="spring.datasource.hikari")
	public HikariConfig hikariconfig() {
		return new HikariConfig();
	}
	
	
	@Bean
	public DataSource dataSource() throws Exception{
		DataSource dataSource = new HikariDataSource(hikariconfig());
		System.out.println(dataSource.toString());
		return dataSource;
	}
	
	
	@Bean
	@ConfigurationProperties(prefix="spring.jpa")
	public Properties hibernateConfig()	{
		return new Properties();
	}
	
	
	
	

	

}
