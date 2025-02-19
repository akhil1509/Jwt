package com.sahani.student.service;


import com.sahani.student.exception.ResouceNotFoundException;
import com.sahani.student.model.Student;
import com.sahani.student.model.User;
import com.sahani.student.repository.StudentRepository;
import com.sahani.student.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class StdServiceImpl implements StdService {

	
   private StudentRepository studentRepository;
   

  



	public StdServiceImpl(StudentRepository studentRepository) {
		
		this.studentRepository = studentRepository;
	}



	@Override
    public Student createStd(Student std) {
        return studentRepository.save(std);
    }
    
  

    @Override
    public Student getStdById(Long id) {
        Student couldNotFind = studentRepository.findById(id).orElseThrow(()-> new ResouceNotFoundException("Student is not available on current id-"+id));;
        return couldNotFind;
    }

    @Override
    public List<Student> allStd() {
        return studentRepository.findAll();
    }

   
}
