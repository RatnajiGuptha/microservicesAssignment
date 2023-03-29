package com.guptha.gateway;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class JwtSecurityApplication {

	private static final Logger LOGGER = LoggerFactory.getLogger(JwtSecurityApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(JwtSecurityApplication.class, args);
		LOGGER.info("Gateway server is running on port : 8000");
	}

}
