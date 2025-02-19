package com.sahani.student.service;



import org.springframework.stereotype.Service;

import com.sahani.student.model.User;
import com.sahani.student.repository.UserRepository;
@Service
public class UserServiceImpl implements UserService {

	
	private UserRepository userRepository;
	
	public UserServiceImpl(UserRepository userRepository) {
		
		this.userRepository = userRepository;
	}

	@Override
	public User createUser(User user) {
		
		return userRepository.save(user);
	}

}
