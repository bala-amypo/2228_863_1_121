package com.example.demo.service;

import com.example.demo.exception.ApiException;
import com.example.demo.model.Student;
import com.example.demo.repository.StudentRepository;

import java.util.List;

public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;

    public StudentServiceImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public Student addStudent(Student student) {

        if (student.getRollNumber() == null ||
            student.getYear() == null ||
            student.getYear() < 1 ||
            student.getYear() > 5) {
            throw new ApiException("year");
        }

        studentRepository.findByRollNumber(student.getRollNumber())
                .ifPresent(s -> { throw new ApiException("exists"); });

        return studentRepository.save(student);
    }

    @Override
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }
}
