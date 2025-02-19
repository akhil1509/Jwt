package com.sahani.student.repository;



import org.springframework.data.jpa.repository.JpaRepository;

import com.sahani.student.model.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository  extends JpaRepository<User, Long> {

   
    Optional<User> findByUsernameOrEmail(String username, String email);
    
    Boolean existsByUsername(String username);
    
    Boolean existsByEmail(String email);
   
}
