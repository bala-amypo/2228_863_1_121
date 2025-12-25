package com.example.demo.service.impl;

import com.example.demo.exception.ApiException;
import com.example.demo.model.ExamSession;
import com.example.demo.repository.ExamSessionRepository;
import com.example.demo.repository.StudentRepository;
import com.example.demo.service.ExamSessionService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class ExamSessionServiceImpl implements ExamSessionService {

    private final ExamSessionRepository sessionRepo;
    private final StudentRepository studentRepo;

    // ✅ THIS FIXES EVERYTHING
    public ExamSessionServiceImpl(ExamSessionRepository sessionRepo,
                                  StudentRepository studentRepo) {
        this.sessionRepo = sessionRepo;
        this.studentRepo = studentRepo;
    }

    @Override
    public ExamSession createSession(ExamSession s) {

        if (s.getExamDate().isBefore(LocalDate.now()))
            throw new ApiException("past");

        if (s.getStudents() == null || s.getStudents().isEmpty())
            throw new ApiException("at least 1 student");

        return sessionRepo.save(s);
    }

    @Override
    public ExamSession getSession(Long id) {
        return sessionRepo.findById(id)
                .orElseThrow(() -> new ApiException("session not found"));
    }
}
