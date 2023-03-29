package com.guptha.gateway.controller;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.guptha.gateway.models.JwtRequest;
import com.guptha.gateway.models.JwtResponse;
import com.guptha.gateway.models.UserInfo;
import com.guptha.gateway.repos.UserInfoRepository;
import com.guptha.gateway.security.JwtService;
import com.guptha.gateway.security.UserInfoUserDetailsService;

@RestController
public class UserController {

	private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

	@Autowired
	private JwtService jwtService;

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private UserInfoUserDetailsService userDetailsService;

	@Autowired
	private UserInfoRepository repo;

	@Autowired
	private PasswordEncoder encoder;

	@PostMapping("/login")
	public ResponseEntity<?> createToken(@RequestBody JwtRequest jwtRequest) throws Exception {
		try {
			authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(jwtRequest.getUsername(), jwtRequest.getPassword()));
		} catch (UsernameNotFoundException e) {
			LOGGER.error("User not found", e);
			return ResponseEntity.badRequest().body("User not found");
		} catch (BadCredentialsException e) {
			LOGGER.error("Bad Credentials", e);
			return ResponseEntity.badRequest().body("Bad Credential");
		}

		UserDetails userDetails = userDetailsService.loadUserByUsername(jwtRequest.getUsername());
		String token = this.jwtService.generateToken(userDetails.getUsername());
		LOGGER.info("Token generated for user {}", userDetails.getUsername());
		return ResponseEntity.ok(new JwtResponse(token));

	}

	@GetMapping("/validateToken")
	public String ValidateJwtToken(@RequestParam String token) {
		jwtService.validateJwtToken(token);
		LOGGER.info("Validating token: {}", token);
		return "Token is valid";
	}

	@PostMapping("/addUser")
	public ResponseEntity<?> addUser(@RequestBody UserInfo u) {
		Optional<UserInfo> name = repo.findByName(u.getName());
		if (!name.isEmpty()) {
			LOGGER.error("User already exists with name {}", u.getName());
			return ResponseEntity.badRequest().body("User already Exists");
		}
		u.setRoles("ROLE_USER");
		u.setPassword(encoder.encode(u.getPassword()));
		repo.save(u);
		LOGGER.info("User added successfully with name {}", u.getName());
		return ResponseEntity.ok("User added sucessfully");

	}

	@GetMapping("hello")
	public String hello() {
		return "hello";
	}

}
