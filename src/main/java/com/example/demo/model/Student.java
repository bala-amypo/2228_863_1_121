package com.example.demo.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(
        name = "students",
        uniqueConstraints = @UniqueConstraint(columnNames = "rollNumber")
)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String rollNumber;

    private String name;
    private String department;
    private Integer year;
}
