package com.example.demo.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;
import java.util.Set;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ExamSession {

    @Id
    @GeneratedValue
    private Long id;

    private String courseCode;
    @Column(name = "exam_date")
    private LocalDate examDate;
    private String examTime;

    @ManyToMany
    private Set<Student> students;
}
