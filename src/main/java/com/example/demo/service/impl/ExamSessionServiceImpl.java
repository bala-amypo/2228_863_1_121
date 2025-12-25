package com.example.demo.service.impl;

import com.example.demo.exception.ApiException;
import com.example.demo.model.ExamSession;
import com.example.demo.model.Student;
import com.example.demo.repository.ExamSessionRepository;
import com.example.demo.repository.StudentRepository;
import com.example.demo.service.ExamSessionService;

import java.time.LocalDate;
import java.util.Set;

public class ExamSessionServiceImpl implements ExamSessionService {
    private final ExamSessionRepository repo;
    private final StudentRepository studentRepo;

    public ExamSessionServiceImpl(ExamSessionRepository repo, StudentRepository studentRepo){
        this.repo = repo; this.studentRepo = studentRepo;
    }

    @Override
    public ExamSession createSession(ExamSession s){
        if(s == null) throw new ApiException("Invalid session");
        if(s.getExamDate()==null || s.getExamDate().isBefore(LocalDate.now())) throw new ApiException("Exam date is in the past");
        Set<Student> students = s.getStudents();
        if(students == null || students.isEmpty()) throw new ApiException("At least 1 student required");
        return repo.save(s);
    }

    @Override
    public ExamSession getSession(Long id){
        return repo.findById(id).orElseThrow(() -> new ApiException("Session not found"));
    }
}
