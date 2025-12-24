package com.example.demo.model;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.Set;

@Entity
@Table(name = "exam_sessions")
public class ExamSession {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String courseCode;

    private LocalDate examDate;

    private String examTime;

    @ManyToMany
    @JoinTable(
            name = "exam_session_students",
            joinColumns = @JoinColumn(name = "exam_session_id"),
            inverseJoinColumns = @JoinColumn(name = "student_id")
    )
    private Set<Student> students;

    public ExamSession() {}

    public Long getId() {
        return id;
    }

    public String getCourseCode() {
        return courseCode;
    }

    public LocalDate getExamDate() {
        return examDate;
    }

    public String getExamTime() {
        return examTime;
    }

    public Set<Student> getStudents() {
        return students;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }

    public void setExamDate(LocalDate examDate) {
        this.examDate = examDate;
    }

    public void setExamTime(String examTime) {
        this.examTime = examTime;
    }

    public void setStudents(Set<Student> students) {
        this.students = students;
    }
}
