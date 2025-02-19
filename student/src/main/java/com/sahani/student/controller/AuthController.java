package com.sahani.student.controller;


import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sahani.student.dto.JwtAuthResponse;
import com.sahani.student.dto.LoginDto;
import com.sahani.student.dto.RegisterDto;
import com.sahani.student.model.User;
import com.sahani.student.service.AuthServiceImpl;
import com.sahani.student.service.UserServiceImpl;

@RestController
@RequestMapping("/user")
public class AuthController {
	
      private AuthServiceImpl authServiceImpl;
     


	public AuthController(AuthServiceImpl authServiceImpl) {
		
		this.authServiceImpl = authServiceImpl;
	}



	@PostMapping("/register")
	public ResponseEntity<String> register(@RequestBody RegisterDto registerDto){
		String registration = authServiceImpl.createRegistration(registerDto);
		return new ResponseEntity<>(registration, HttpStatus.CREATED);
	}
	

	@PostMapping("/login")
	public ResponseEntity<JwtAuthResponse> login(@RequestBody LoginDto loginDto){
		String token = authServiceImpl.createLogin(loginDto);
		JwtAuthResponse response = new JwtAuthResponse();
		response.setAccessToken(token);
		return new ResponseEntity<>(response, HttpStatus.CREATED);
	}
}
