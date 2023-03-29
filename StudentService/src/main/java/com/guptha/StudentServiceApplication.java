package com.guptha;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class StudentServiceApplication {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(StudentServiceApplication.class);
	
	public static void main(String[] args) {
		SpringApplication.run(StudentServiceApplication.class, args);
		LOGGER.info("Student service is running on port no : 8200");
	}

}
