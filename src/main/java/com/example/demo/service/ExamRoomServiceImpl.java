package com.example.demo.service;

import com.example.demo.model.ExamRoom;
import com.example.demo.repository.ExamRoomRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExamRoomServiceImpl implements ExamRoomService {

    private final ExamRoomRepository repository;

    public ExamRoomServiceImpl(ExamRoomRepository repository) {
        this.repository = repository;
    }

    @Override
    public ExamRoom addRoom(ExamRoom room) {
        return repository.save(room);
    }

    @Override
    public List<ExamRoom> getAllRooms() {
        return repository.findAll();
    }
}
