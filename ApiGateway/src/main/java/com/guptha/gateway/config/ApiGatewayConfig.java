package com.guptha.gateway.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApiGatewayConfig {

	@Bean
	RouteLocator gateWayRouter(RouteLocatorBuilder builder) {
		return builder.routes()
				.route(p -> p.path("/getAll").uri("lb://department-service"))
				.route(p -> p.path("/getByDeptId/**").uri("lb://student-service"))
				.route(p -> p.path("/getAllStudents").uri("lb://student-service"))
				.route(p -> p.path("/getAllDepartments").uri("lb://student-service"))
				.route(p -> p.path("/getAllStudentsByDept/**").uri("lb://student-service"))
				.route(p -> p.path("/students/**").uri("lb://student-service"))
				.build();
	}
}
