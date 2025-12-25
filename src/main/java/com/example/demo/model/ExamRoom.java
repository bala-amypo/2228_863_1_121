package com.example.demo.model;

import jakarta.persistence.*;

@Entity
@Table(name = "exam_room")
public class ExamRoom {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String roomNumber;

    @Column(name = "row_count")
    private Integer rowCount;

    @Column(name = "column_count")
    private Integer columnCount;

    private Integer capacity;

    // ----- getters -----

    public Long getId() {
        return id;
    }

    public String getRoomNumber() {
        return roomNumber;
    }

    public Integer getRowCount() {
        return rowCount;
    }

    public Integer getColumnCount() {
        return columnCount;
    }

    public Integer getCapacity() {
        return capacity;
    }

    // ----- helper method used by service -----
    public void ensureCapacityMatches() {
        if (rowCount != null && columnCount != null) {
            this.capacity = rowCount * columnCount;
        }
    }
}
