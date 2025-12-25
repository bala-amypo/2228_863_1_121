package com.example.demo.controller;

import com.example.demo.model.ExamSession;
import com.example.demo.service.ExamSessionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/sessions") 
public class ExamSessionController {

    private final ExamSessionService service;
    public ExamSessionController(ExamSessionService service){ this.service = service; }


    @PostMapping
    public ResponseEntity<ExamSession> create(ExamSession s){
        return ResponseEntity.ok(service.createSession(s));
    }
    @GetMapping("/{id}")
  public ResponseEntity<ExamSession> get(Long id){
        return ResponseEntity.ok(service.getSession(id));
    }
}
