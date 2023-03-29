package com.guptha;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class DepartmentServiceApplication {

	private static final Logger LOGGER = LoggerFactory.getLogger(DepartmentServiceApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(DepartmentServiceApplication.class, args);
		LOGGER.info("Department Service is running on port no : 8100");
	}

}
