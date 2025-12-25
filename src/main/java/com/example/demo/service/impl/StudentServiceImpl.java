package com.example.demo.service.impl;

import com.example.demo.exception.ApiException;
import com.example.demo.model.Student;
import com.example.demo.repository.StudentRepository;
import com.example.demo.service.StudentService;

import java.util.List;

public class StudentServiceImpl implements StudentService {
    private final StudentRepository repo;
    public StudentServiceImpl(StudentRepository repo){ this.repo = repo; }

    @Override
    public Student addStudent(Student s){
        if(s == null || s.getRollNumber()==null || s.getName()==null) throw new ApiException("Missing fields");
        Integer year = s.getYear();
        if(year == null || year < 1 || year > 5) throw new ApiException("Invalid year");
        if(repo.findByRollNumber(s.getRollNumber()).isPresent()) throw new ApiException("Student with roll exists");
        return repo.save(s);
    }

    @Override
    public List<Student> getAllStudents(){ return repo.findAll(); }
}
