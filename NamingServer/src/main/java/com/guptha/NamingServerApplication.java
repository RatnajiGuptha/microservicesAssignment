package com.guptha;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class NamingServerApplication {

	private static final Logger logger = LoggerFactory.getLogger(NamingServerApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(NamingServerApplication.class, args);
		logger.info("Naming eureka server is running on port : 8761");
	}

}
