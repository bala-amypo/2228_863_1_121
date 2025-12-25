package com.example.demo.controller;

import com.example.demo.model.Student;
import com.example.demo.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/students")
@RequiredArgsConstructor

public class StudentController {

     private final StudentService service;
    public StudentController(StudentService service){ this.service = service; }

    @PostMapping
    public ResponseEntity<Student> add(Student s){
        return ResponseEntity.ok(service.addStudent(s));
    }

    @GetMapping
    public ResponseEntity<List<Student>> list(){
        return ResponseEntity.ok(service.getAllStudents());
    }
}
