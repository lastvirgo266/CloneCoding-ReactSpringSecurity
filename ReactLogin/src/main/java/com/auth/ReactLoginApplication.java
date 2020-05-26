package com.auth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@PropertySource("classpath:jwt.yml")
public class ReactLoginApplication {

	public static void main(String[] args) {
		SpringApplication.run(ReactLoginApplication.class, args);
	}

}
