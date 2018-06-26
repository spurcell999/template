package com.project.template;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
public class SendBlueprintApplication extends SpringBootServletInitializer{

	public static void main(String[] args) {
		SpringApplication.run(SendBlueprintApplication.class, args);
	}
}
