package com.sahani.student.service;

import com.sahani.student.dto.LoginDto;
import com.sahani.student.dto.RegisterDto;

public interface AuthService {
	
	public String createRegistration(RegisterDto registerDto);
	public String createLogin(LoginDto loginDto);

	
}
