package com.sahani.student.controller;





import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.sahani.student.model.Student;
import com.sahani.student.service.StdServiceImpl;

import java.util.List;

@RestController
@RequestMapping("/std")
public class StdController {

    private StdServiceImpl stdServiceImpl;

    public StdController(StdServiceImpl stdServiceImpl) {
        this.stdServiceImpl = stdServiceImpl;
    }

    @PostMapping("/add")
    public ResponseEntity<Student> addStd(@RequestBody Student std){
    	Student std1 = stdServiceImpl.createStd(std);
        return new ResponseEntity<>(std1, HttpStatus.CREATED);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<Student> getStdbyId(@PathVariable Long id){
    	Student stdById = stdServiceImpl.getStdById(id);
        return new ResponseEntity<>(stdById, HttpStatus.FOUND);
    }

    @GetMapping("/getAll")
    public  ResponseEntity<List<Student>> getAllStd(){
        List<Student> stds = stdServiceImpl.allStd();
        return new ResponseEntity<>(stds, HttpStatus.OK);
    }

}
