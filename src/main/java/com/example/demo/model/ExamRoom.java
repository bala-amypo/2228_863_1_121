package com.example.demo.model;
import jakarta.presistence.*;

@Entity 
public class ExamRoom{
    @Id
    @GeneratedValue

    private Long id;
    @Column(unique = true )

    private String roomNumber;
    pr
}