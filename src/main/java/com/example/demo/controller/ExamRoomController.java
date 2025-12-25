package com.example.demo.controller;

import com.example.demo.model.ExamRoom;
import com.example.demo.service.ExamRoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/rooms")
public class ExamRoomController {

    private final ExamRoomService examRoomService;

    public ExamRoomController(ExamRoomService examRoomService) {
        this.examRoomService = examRoomService;
    }
    @PostMapping
        public ResponseEntity<ExamRoom> add(ExamRoom r){
        return ResponseEntity.ok(service.addRoom(r));
    }


    @GetMapping
    public ResponseEntity<List<ExamRoom>> list(){
        return ResponseEntity.ok(service.getAllRooms());
    }
}
