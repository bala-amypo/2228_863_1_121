package com.example.demo.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter @Setter @Builder
@NoArgsConstructor @AllArgsConstructor
@Table(name = "exam_room")
public class ExamRoom {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String roomNumber;

    private Integer rowses;
    private Integer columnses;
    private Integer capacity;

    public void ensureCapacityMatches() {
        if (rowsese != null && columnses != null) {
            this.capacity = rowses * columnses;
        }
    }
}
