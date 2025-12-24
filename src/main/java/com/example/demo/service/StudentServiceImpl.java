package com.example.demo.service;

import com.example.demo.exception.ApiException;
import com.example.demo.model.Student;
import com.example.demo.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;

    // ⚠️ Exact constructor signature
    public StudentServiceImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public Student addStudent(Student student) {
        if (studentRepository.findByRollNumber(student.getRollNumber()).isPresent()) {
            throw new ApiException("exists");
        }
        if (student.getYear() < 1 || student.getYear() > 5) {
            throw new ApiException("year");
        }
        return studentRepository.save(student);
    }

    @Override
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }
}
