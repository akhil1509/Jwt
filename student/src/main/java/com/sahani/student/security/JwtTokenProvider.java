package com.sahani.student.security;

import java.security.Key;
import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;


import io.jsonwebtoken.Jwts;

import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtTokenProvider {

	//@Value("${app.jwt-secret}")
	private final String jwtSecret ="akhilesh";
	//@Value("${app-jwt-expiration-milliseconds}")
	private final long jwtExpirationDate = 1000 * 60 * 60;

	private UserDetailsService userDetailsService;
	@Autowired
	public JwtTokenProvider(UserDetailsService userDetailsService) {
		
		this.userDetailsService = userDetailsService;
	}

	// Generate JWT Token
	public String generateToken(Authentication authentication) {
		String name = authentication.getName();
		Date date = new Date();

		Date expire = new Date(date.getTime() + jwtExpirationDate);

		String token = Jwts.builder().subject(name).issuedAt(date).expiration(expire).signWith(Key()).compact();

		return token;
	}

	private Key Key() {

		return Keys.secretKeyFor(io.jsonwebtoken.SignatureAlgorithm.HS256);
	}

	// get username from JWT token

	public String getUserName(String token) {
		return Jwts.parser().verifyWith((SecretKey) Key()).build().parseSignedClaims(token).getPayload().getSubject();

	}
	
	// // validate JWT token
	
	public Boolean validateToken(String token) {
		Jwts.parser()
		.verifyWith((SecretKey) Key())
		.build()
		.parse(token);
		
		return true;
	}

}
