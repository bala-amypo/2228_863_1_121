package com.example.demo.repository;

import com.example.demo.model.ExamRoom;
import java.util.*;

public interface ExamRoomRepository {
    Optional<ExamRoom> findByRoomNumber(String roomNumber);
    ExamRoom save(ExamRoom r);
    List<ExamRoom> findAll();
    List<ExamRoom> findByCapacityGreaterThanEqual(int capacity);
}
