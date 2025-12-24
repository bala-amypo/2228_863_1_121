package com.example.demo.repository;

import com.example.demo.model.ExamSession;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface ExamSessionRepository extends JpaRepository<ExamSession, Long> {

    // REQUIRED by tests
    List<ExamSession> findByExamDate(LocalDate examDate);

    // Standard Spring Data method
    Optional<ExamSession> findById(Long id);
}
