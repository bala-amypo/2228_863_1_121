package com.example.demo.service;

import com.example.demo.exception.ApiException;
import com.example.demo.model.ExamRoom;
import com.example.demo.repository.ExamRoomRepository;

import java.util.List;

public class ExamRoomServiceImpl implements ExamRoomService {

    private final ExamRoomRepository examRoomRepository;

    public ExamRoomServiceImpl(ExamRoomRepository examRoomRepository) {
        this.examRoomRepository = examRoomRepository;
    }

    @Override
    public ExamRoom addRoom(ExamRoom room) {

        if (room.getRows() == null || room.getColumns() == null ||
            room.getRows() <= 0 || room.getColumns() <= 0) {
            throw new ApiException("invalid room");
        }

        examRoomRepository.findByRoomNumber(room.getRoomNumber())
                .ifPresent(r -> { throw new ApiException("exists"); });

        room.setCapacity(room.getRows() * room.getColumns());
        return examRoomRepository.save(room);
    }

    @Override
    public List<ExamRoom> getAllRooms() {
        return examRoomRepository.findAll();
    }
}
