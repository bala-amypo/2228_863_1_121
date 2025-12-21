package com.example.demo.service;

import com.example.demo.model.ExamSession;
import com.example.demo.repository.ExamSessionRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExamSessionServiceImpl implements ExamSessionService {

    private final ExamSessionRepository repository;

    public ExamSessionServiceImpl(ExamSessionRepository repository) {
        this.repository = repository;
    }

    @Override
    public ExamSession createSession(ExamSession session) {
        return repository.save(session);
    }

    @Override
    public ExamSession getSession(Long id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public List<ExamSession> getAllSessions() {
        return repository.findAll();
    }
}
