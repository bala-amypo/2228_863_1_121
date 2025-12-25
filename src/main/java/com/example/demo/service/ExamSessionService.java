package com.example.demo.service;

import com.example.demo.model.ExamSession;

public interface ExamSessionService {
    ExamSession createSession(ExamSession s);
    ExamSession getSession(Long id);
}
