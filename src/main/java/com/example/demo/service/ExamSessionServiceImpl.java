package com.example.demo.service;

import com.example.demo.model.ExamSession;
import com.example.demo.repository.ExamSessionRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExamSessionServiceImpl implements ExamSessionService {

    private final ExamSessionRepository examSessionRepository;

    public ExamSessionServiceImpl(ExamSessionRepository examSessionRepository) {
        this.examSessionRepository = examSessionRepository;
    }

    @Override
    public ExamSession createSession(ExamSession session) {
        return examSessionRepository.save(session);
    }

    @Override
    public ExamSession getSession(Long id) {
        return examSessionRepository.findById(id).orElse(null);
    }

    @Override
    public List<ExamSession> getAllSessions() {
        return examSessionRepository.findAll();
    }
}
