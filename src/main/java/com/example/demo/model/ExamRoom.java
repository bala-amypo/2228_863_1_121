package com.example.demo.model;
import jakarta.presistence.*;

@Entity 
public class ExamRoom{
    @Id
    @GeneratedValue

    private Long id;
    @Column(unique = true )

    private String roomNumber;
    private Integer rows;
    private Integer columns;
    private Integer capacity;


    @prepersist
    @preUpdate

    public void ensureCapacitymatches()
    {
        this.capacity=rows * columns;
    }
}