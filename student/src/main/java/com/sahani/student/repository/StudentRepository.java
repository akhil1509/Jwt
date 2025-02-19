package com.sahani.student.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sahani.student.model.Student;

public interface StudentRepository extends JpaRepository<Student, Long> {

}
