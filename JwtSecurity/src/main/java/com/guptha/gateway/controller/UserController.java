package com.guptha.gateway.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.guptha.gateway.models.JwtRequest;
import com.guptha.gateway.models.JwtResponse;
import com.guptha.gateway.security.service.JwtService;
import com.guptha.gateway.security.service.UserInfoUserDetailsService;

@RestController
public class UserController {

	@Autowired
	private JwtService jwtService;

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private UserInfoUserDetailsService userDetailsService;

	@PostMapping("/login")
	public ResponseEntity<?> createToken(@RequestBody JwtRequest jwtRequest) throws Exception {
		try {
			authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(jwtRequest.getUsername(), jwtRequest.getPassword()));
		} catch (UsernameNotFoundException e) {
			return ResponseEntity.badRequest().body("User not found");
		} catch (BadCredentialsException e) {
			return ResponseEntity.badRequest().body("Bad Credential");
		}

		UserDetails userDetails = userDetailsService.loadUserByUsername(jwtRequest.getUsername());
		String token = this.jwtService.generateToken(userDetails.getUsername());
		System.err.println(token);
		return ResponseEntity.ok(new JwtResponse(token));

	}

	@GetMapping("/hi")
	public String hello() {
		return "hello";
	}

	@GetMapping("/validateToken")
	public String ValidateJwtToken(@RequestParam String token) {
		jwtService.validateJwtToken(token);
		return "Token is valid";
	}

}
