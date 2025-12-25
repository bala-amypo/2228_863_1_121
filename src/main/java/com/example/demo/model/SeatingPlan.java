package com.example.demo.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class SeatingPlan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private ExamSession examSession;

    @ManyToOne
    private ExamRoom room;

    @Lob
    private String arrangementJson;

    private LocalDateTime generatedAt;

    public SeatingPlan(){}

    public static Builder builder(){ return new Builder(); }
    public static class Builder{
        private final SeatingPlan p = new SeatingPlan();
        public Builder id(Long id){ p.setId(id); return this; }
        public Builder examSession(ExamSession v){ p.setExamSession(v); return this; }
        public Builder room(ExamRoom v){ p.setRoom(v); return this; }
        public Builder arrangementJson(String v){ p.setArrangementJson(v); return this; }
        public Builder generatedAt(LocalDateTime v){ p.setGeneratedAt(v); return this; }
        public SeatingPlan build(){ return p; }
    }

    public Long getId(){ return id; }
    public void setId(Long id){ this.id = id; }
    public ExamSession getExamSession(){ return examSession; }
    public void setExamSession(ExamSession examSession){ this.examSession = examSession; }
    public ExamRoom getRoom(){ return room; }
    public void setRoom(ExamRoom room){ this.room = room; }
    public String getArrangementJson(){ return arrangementJson; }
    public void setArrangementJson(String arrangementJson){ this.arrangementJson = arrangementJson; }
    public LocalDateTime getGeneratedAt(){ return generatedAt; }
    public void setGeneratedAt(LocalDateTime generatedAt){ this.generatedAt = generatedAt; }
}
