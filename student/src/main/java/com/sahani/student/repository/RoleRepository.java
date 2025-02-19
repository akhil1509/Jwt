package com.sahani.student.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sahani.student.model.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {

    Role findByName(String name);
}