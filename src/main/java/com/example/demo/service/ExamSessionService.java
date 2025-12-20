package com.example.demo.service;

import com.example.demo.model.ExamSession;
import java.util.List;

public interface ExamSessionService {
    ExamSession save(ExamSession session);
    List<ExamSession> getAll();
}
