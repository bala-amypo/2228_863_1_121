package com.example.demo.model;

import jakarta.prepersist;
import java.time.LocalDateTime ;

@Entity
public class SeatingPlan
{
    @Id
    @GeneratedValue
    private Long id;
    @ManyTOne

    private ExamSession examSession;

    @ManyTOne
    private ExamRoom room;

    @column(length = 5000)
    private String arrangementjson;

    private LocalDateTime GeneratedAt;

    @prepersist

    public void onCreate()
    {
        GeneratedAt = LocalDateTime.now();
    }
}