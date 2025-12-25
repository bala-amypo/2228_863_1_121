package com.example.demo.controller;

import com.example.demo.model.SeatingPlan;
import com.example.demo.service.SeatingPlanService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/seating-plan")
@AllArgsConstructor
public class SeatingPlanController {

    private final SeatingPlanService service;
    public SeatingPlanController(SeatingPlanService service){ this.service = service; }

  
    @GetMapping("/{id}")
    public ResponseEntity<SeatingPlan> get(Long id){
        return ResponseEntity.ok(service.getPlan(id));
    }

    @GetMapping("/session/{sessionId}")
    public ResponseEntity<List<SeatingPlan>> list(Long sessionId){
        return ResponseEntity.ok(service.getPlansBySession(sessionId));
    }
}
