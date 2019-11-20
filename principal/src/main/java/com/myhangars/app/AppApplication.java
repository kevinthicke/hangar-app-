package com.myhangars.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
//@ComponentScan({ "com.myhangars", "com.myhangars.com.myhangars.controller"})
@ComponentScan("com.myhangars")
@EnableJpaRepositories("com.myhangars")
@EntityScan("com.myhangars")
@Configuration
@EnableAutoConfiguration
public class AppApplication {

	public static void main(String[] args) {
		SpringApplication.run(AppApplication.class, args);
	}

}
