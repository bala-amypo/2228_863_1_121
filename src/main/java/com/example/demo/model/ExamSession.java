package com.example.demo.model;

import jakarta.presistence.*;
import java.time.LocalDate;
importjava.util.List;

@Entity
public class ExamSession{
    @ID
    @GeneratedValue

    private Long id;

    private String courseCode;
    private LocalDate examDate;
    private String examTime;

    @ManyToMany
}
