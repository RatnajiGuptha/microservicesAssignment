package com.guptha.gateway.filter;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.security.Key;

@Component
public class JwtUtillService {

	public static final long JWT_TOKEN_VALIDITY = 1 * 60 * 60;

	private static final String jwtSecret = "5367566B59703373367639792F423F4528482B4D6251655468576D5A71347437";;

	private Key getSignKey() {
		byte[] keyBytes = Decoders.BASE64.decode(jwtSecret);
		return Keys.hmacShaKeyFor(keyBytes);
	}

	public void validateJwtToken(String token) {
		Jwts.parserBuilder().setSigningKey(getSignKey()).build().parseClaimsJws(token).getBody();
	}
}
