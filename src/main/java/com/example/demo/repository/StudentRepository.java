package com.example.demo.repository;

import com.example.demo.model.Student;
import java.util.*;

public interface StudentRepository {
    Optional<Student> findByRollNumber(String rollNumber);
    Student save(Student s);
    List<Student> findAll();
}
