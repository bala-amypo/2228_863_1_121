package com.example.demo.controller;

import com.example.demo.model.ExamRoom;
import com.example.demo.service.ExamRoomService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rooms")
@Tag(name = "Exam Rooms")
public class ExamRoomController {

    private final ExamRoomService examRoomService;

    public ExamRoomController(ExamRoomService examRoomService) {
        this.examRoomService = examRoomService;
    }

    @Operation(summary = "Add a new exam room")
    @PostMapping
    public ExamRoom addRoom(@RequestBody ExamRoom room) {
        return examRoomService.addRoom(room);
    }

    @Operation(summary = "Get all exam rooms")
    @GetMapping
    public List<ExamRoom> getAllRooms() {
        return examRoomService.getAllRooms();
    }
}
