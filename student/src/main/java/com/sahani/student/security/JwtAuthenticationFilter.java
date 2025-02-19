package com.sahani.student.security;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;


import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component

public class JwtAuthenticationFilter  extends OncePerRequestFilter{

	
	
	private JwtTokenProvider jwtTokenProvider;
	private CustomUserDetailsService customUserDetailsService;
	public JwtAuthenticationFilter(JwtTokenProvider jwtTokenProvider, CustomUserDetailsService customUserDetailsService) {
		
		this.jwtTokenProvider = jwtTokenProvider;
		this.customUserDetailsService = customUserDetailsService;
	}
	@Override
	protected void doFilterInternal(HttpServletRequest request,
			HttpServletResponse response, 
			FilterChain filterChain)
			throws ServletException, IOException {
		
		//get token from http request from jwttoken provider
		String tokenHttpRequest = getTokenHttpRequest(request);
		
		//validate the token
		if(StringUtils.hasText(tokenHttpRequest) &&  jwtTokenProvider.validateToken(tokenHttpRequest)) {
			// get username from token
			String userName = jwtTokenProvider.getUserName(tokenHttpRequest);
			//get user object from db
			UserDetails userByUsername = customUserDetailsService.loadUserByUsername(userName);
			
			UsernamePasswordAuthenticationToken tk = new UsernamePasswordAuthenticationToken(
					userByUsername,
					null,
					userByUsername.getAuthorities()
					
					);
			
			tk.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

	            SecurityContextHolder.getContext().setAuthentication(tk);
		}
		filterChain.doFilter(request, response);
		
	}
	

	public String getTokenHttpRequest(HttpServletRequest request) {
		String headerToken = request.getHeader("Authentication");
		if(StringUtils.hasText(headerToken) && headerToken.startsWith("Bearer")) {
			return headerToken.substring(7,headerToken.length());
			
		}
		return null;
	}
	 

}
