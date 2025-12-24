package com.example.demo.service.impl;

import com.example.demo.exception.ApiException;
import com.example.demo.model.ExamSession;
import com.example.demo.repository.ExamSessionRepository;
import com.example.demo.service.ExamSessionService;
import org.springframework.stereotype.Service;

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
    public ExamSession getSession(Long sessionId) {
        return examSessionRepository.findById(sessionId)
                .orElseThrow(() -> new ApiException("session not found"));
    }
}
