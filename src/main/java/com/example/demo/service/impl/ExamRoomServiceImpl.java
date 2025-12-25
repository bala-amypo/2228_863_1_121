package com.example.demo.service.impl;

import com.example.demo.exception.ApiException;
import com.example.demo.model.ExamRoom;
import com.example.demo.repository.ExamRoomRepository;
import com.example.demo.service.ExamRoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ExamRoomServiceImpl implements ExamRoomService {

    private final ExamRoomRepository examRoomRepository;

    @Override
    public ExamRoom addRoom(ExamRoom room) {

        if (examRoomRepository.findByRoomNumber(room.getRoomNumber()).isPresent()) {
            throw new ApiException("Room with number " + room.getRoomNumber() + " already exists");
        }

        if (room.getRowCount() < 0) {
            throw new ApiException("Rows cannot be negative");
        }

        if (room.getColumnCount() < 0) {
            throw new ApiException("Columns cannot be negative");
        }

        // auto-calculate capacity
        room.setCapacity(room.getRowCount() * room.getColumnCount());

        return examRoomRepository.save(room);
    }

    @Override
    public List<ExamRoom> getAllRooms() {
        return examRoomRepository.findAll();
    }
}
