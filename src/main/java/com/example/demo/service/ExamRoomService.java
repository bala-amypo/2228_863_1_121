package com.example.demo.service;

import com.example.demo.entity.ExamRoom;
import java.util.List;

public interface ExamRoomService {

    ExamRoom save(ExamRoom examRoom);

    List<ExamRoom> getAll();
}
