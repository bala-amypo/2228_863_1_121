package com.example.demo.controller;

import com.example.demo.model.Student;
import com.example.demo.service.StudentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/students")
@Tag(name = "Students")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @Operation(summary = "Add a new student")
    @PostMapping
    public Student addStudent(@RequestBody Student student) {
        return studentService.addStudent(student);
    }

    @Operation(summary = "Get all students")
    @GetMapping
    public List<Student> getAllStudents() {
        return studentService.getAllStudents();
    }
}
