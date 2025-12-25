package com.example.demo.repository;

import com.example.demo.model.ExamSession;
import java.time.LocalDate;
import java.util.*;

public interface ExamSessionRepository {
    ExamSession save(ExamSession s);
    Optional<ExamSession> findById(Long id);
    List<ExamSession> findByExamDate(LocalDate date);
}
