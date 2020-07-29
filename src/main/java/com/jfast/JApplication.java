package com.jfast;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;


@SpringBootApplication
@Configuration
@EnableTransactionManagement
public class JApplication extends SpringBootServletInitializer{
	
	public static void main(String[] args) {
		SpringApplication.run(JApplication.class, args);
	}
	
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(JApplication.class);
	}
	
}
