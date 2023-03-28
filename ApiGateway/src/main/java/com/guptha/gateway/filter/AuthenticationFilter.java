package com.guptha.gateway.filter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.stereotype.Component;

import com.google.common.net.HttpHeaders;

@Component
public class AuthenticationFilter extends AbstractGatewayFilterFactory<AuthenticationFilter.Config> {

	@Autowired
	private RouterValidator routerValidator;

	@Autowired
	private JwtUtillService jwtUtillService;

	public AuthenticationFilter() {
		super(Config.class);
	}

	public static class Config {

	}

	@Override
	public GatewayFilter apply(Config config) {
		return ((exchange, chain) -> {

			if (routerValidator.isSecured.test(exchange.getRequest())) {
				if (!exchange.getRequest().getHeaders().containsKey(HttpHeaders.AUTHORIZATION)) {
					throw new RuntimeException("Missing authorization token");
				}
				String authHeader = exchange.getRequest().getHeaders().get(HttpHeaders.AUTHORIZATION).get(0);
				if (authHeader != null && authHeader.startsWith("Bearer")) {
					authHeader = authHeader.substring(7);
				}
				try {
					jwtUtillService.validateJwtToken(authHeader);
				} catch (Exception e) {
					throw new RuntimeException("unauthorized access to application");
				}
			}

			return chain.filter(exchange);
		});

	}

}
