package com.guptha.gateway.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.security.Key;

@Component
public class JwtUtillService {

	private static final Logger logger = LoggerFactory.getLogger(JwtUtillService.class);

	private static final String jwtSecret = "5367566B59703373367639792F423F4528482B4D6251655468576D5A71347437";;

	private Key getSignKey() {
		byte[] keyBytes = Decoders.BASE64.decode(jwtSecret);
		return Keys.hmacShaKeyFor(keyBytes);
	}

	public boolean validateJwtToken(final String token) {
		try {
			Jwts.parserBuilder().setSigningKey(getSignKey()).build().parseClaimsJws(token).getBody();
			return true;
		} catch (Exception e) {
			logger.error("Error while validating JWT token", e);
			return false;
		}
	}
}
