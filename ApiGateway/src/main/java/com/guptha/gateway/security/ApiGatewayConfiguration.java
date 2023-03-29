package com.guptha.gateway.security;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

//@Configuration
public class ApiGatewayConfiguration {

	@Bean
	RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
		return builder.routes().route("jwt-security-addUser", r -> r.path("/addUser").uri("lb://jwt-security"))
				.route("jwt-security-login", r -> r.path("/login").uri("lb://jwt-security")).build();
	}
}
