package com.example.demo.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "exam_room")
public class ExamRoom {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String roomNumber;

    
    @Column(name = "room_rows")
    private Integer rows;

    @Column(name = "room_columns")
    private Integer columns;

    private Integer capacity;

    public void ensureCapacityMatches() {
        if (rows != null && columns != null) {
            this.capacity = rows * columns;
        }
    }
}