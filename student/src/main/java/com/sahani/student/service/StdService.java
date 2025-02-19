package com.sahani.student.service;


import com.sahani.student.model.Student;


import java.util.List;

public interface StdService {
   // for create
    Student createStd(Student std);

    //get by id

    Student getStdById(Long id);

    //get All Std
    List<Student> allStd();

    
}
