package com.example.demo.controller;

import com.example.demo.model.ExamSession;
import com.example.demo.service.ExamSessionService;
import org.springframework.http.ResponseEntity;

public class ExamSessionController {
    private final ExamSessionService service;
    public ExamSessionController(ExamSessionService service){ this.service = service; }

    public ResponseEntity<ExamSession> create(ExamSession s){
        return ResponseEntity.ok(service.createSession(s));
    }

    public ResponseEntity<ExamSession> get(Long id){
        return ResponseEntity.ok(service.getSession(id));
    }
}
