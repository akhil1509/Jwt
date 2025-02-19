package com.sahani.student.service;

import java.util.HashSet;
import java.util.Set;

import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.sahani.student.dto.LoginDto;
import com.sahani.student.dto.RegisterDto;
import com.sahani.student.exception.ResouceNotFoundException;
import com.sahani.student.model.Role;
import com.sahani.student.model.User;
import com.sahani.student.repository.RoleRepository;
import com.sahani.student.repository.UserRepository;
import com.sahani.student.security.JwtTokenProvider;





@Service

public class AuthServiceImpl implements AuthService {
	
	private UserRepository userRepository;
	private RoleRepository roleRepository;
	private PasswordEncoder passwordEncoder;
	private AuthenticationManager authenticationManager;
	private JwtTokenProvider jwtTokenProvider;
	public AuthServiceImpl(UserRepository userRepository, RoleRepository roleRepository,
			PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager,
			JwtTokenProvider jwtTokenProvider) {
		
		this.userRepository = userRepository;
		this.roleRepository = roleRepository;
		this.passwordEncoder = passwordEncoder;
		this.authenticationManager = authenticationManager;
		this.jwtTokenProvider = jwtTokenProvider;
	}



	@Override
	public String createRegistration(RegisterDto registerDto) {
		// by username
		if(userRepository.existsByUsername(registerDto.getUsername())) {
			throw new ResouceNotFoundException(HttpStatus.BAD_REQUEST + "already exist user");
		}
		
		//by emai id
		
	if(userRepository.existsByEmail(registerDto.getEmail())) {
			throw new ResouceNotFoundException(HttpStatus.BAD_REQUEST + "already exist user");
		}
		
		User user = new User();
		user.setName(registerDto.getName());
		user.setUsername(registerDto.getUsername());
		user.setEmail(registerDto.getEmail());
		user.setPassword(registerDto.getPassword());
		// get role by database
		Set<Role> roles = new HashSet<>();
		Role userrole = roleRepository.findByName("ROLE_USER");
		roles.add(userrole);
		user.setRoles(roles);
		userRepository.save(user);
		
		return "UserRegisterd successfully";
		
		
	
	}



	@Override
	public String createLogin(LoginDto loginDto) {
		Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
				loginDto.getUsernameoremail(),
				loginDto.getPassword()
				));
		SecurityContextHolder.getContext().setAuthentication(authenticate);
		String token = jwtTokenProvider.generateToken(authenticate);
		return token;
	}

}
