package com.example.demo.controller;

import com.example.demo.model.ExamRoom;
import com.example.demo.service.ExamRoomService;
import org.springframework.http.ResponseEntity;

import java.util.List;

public class ExamRoomController {
    private final ExamRoomService service;
    public ExamRoomController(ExamRoomService service){ this.service = service; }

    public ResponseEntity<ExamRoom> add(ExamRoom r){
        return ResponseEntity.ok(service.addRoom(r));
    }

    public ResponseEntity<List<ExamRoom>> list(){
        return ResponseEntity.ok(service.getAllRooms());
    }
}
