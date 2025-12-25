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

    public Integer getRowCount() {
        return rowCount;
    }

    public Integer getColumnCount() {
        return columnCount;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }

    public String getRoomNumber() {
        return roomNumber;
    }
    public Integer getRowCount() { return rowCount; }
public Integer getColumnCount() { return columnCount; }
public void setCapacity(Integer capacity) { this.capacity = capacity; }
public String getRoomNumber() { return roomNumber; }

}
