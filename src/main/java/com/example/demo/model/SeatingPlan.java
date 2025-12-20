package com.example.demo.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "seating_plans")
public class SeatingPlan {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(length = 5000)
    private String arrangementJson;

    private LocalDateTime generatedAt;

    @ManyToOne
    private ExamSession examSession;

    @ManyToOne
    private ExamRoom room;

    // ✅ REQUIRED: no-arg constructor
    public SeatingPlan() {
    }

    // ✅ GETTERS & SETTERS (MANDATORY)

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getArrangementJson() {
        return arrangementJson;
    }

    public void setArrangementJson(String arrangementJson) {
        this.arrangementJson = arrangementJson;
    }

    public LocalDateTime getGeneratedAt() {
        return generatedAt;
    }

    public void setGeneratedAt(LocalDateTime generatedAt) {
        this.generatedAt = generatedAt;
    }

    public ExamSession getExamSession() {
        return examSession;
    }

    public void setExamSession(ExamSession examSession) {
        this.examSession = examSession;
    }

    public ExamRoom getRoom() {
        return room;
    }

    public void setRoom(ExamRoom room) {
        this.room = room;
    }
}
