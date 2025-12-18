package com.example.demo.model;

import jakarta.presistence.*;

@Entity 
public class Student{
    @Id
    @GeneratedValue

    private long id;

    @Column(unique=true)

    private String rollNumber;
    private String name ;
    private String department;
    private Integer year;

}