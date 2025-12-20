package com.example.demo.model;
import jakarta.persistence.*;
import java.time.LocalDateTime;
@Entity
@Table(name = "seating_plans")

public class SeatingPlan {

    @Id
    @GeneratedValue
    private Long id;
    @ManyToOne
    private ExamSession examSession;
    @ManyToOne
    private ExamRoom room;



    @Column(length = 5000)

    private String arrangementJson;



    private LocalDateTime generatedAt;



    @PrePersist

    public void onCreate() {

        generatedAt = LocalDateTime.now();

    }



    public Long getId() { return id; }

    public ExamSession getExamSession() { return examSession; }

    public ExamRoom getRoom() { return room; }

    public String getArrangementJson() { return arrangementJson; }

    public LocalDateTime getGeneratedAt() { return generatedAt; }



    public void setId(Long id) { this.id = id; }

    public void setExamSession(ExamSession examSession) { this.examSession = examSession; }

    public void setRoom(ExamRoom room) { this.room = room; }

    public void setArrangementJson(String arrangementJson) { this.arrangementJson = arrangementJson; }

}
    