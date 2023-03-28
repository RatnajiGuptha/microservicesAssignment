package com.guptha.gateway.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApiGatewayConfig {

	@Bean
	RouteLocator gateWayRouter(RouteLocatorBuilder builder) {
		return builder.routes().route(p -> p.path("/addUser").uri("lb://jwt-security"))
				.route(p -> p.path("/login").uri("lb://jwt-security"))
//				.route(p -> p.path("/getAllStudents").uri("lb://student-service"))
				.build();
	}
}
