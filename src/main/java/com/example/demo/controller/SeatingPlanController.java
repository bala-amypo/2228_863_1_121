package com.example.demo.controller;

import com.example.demo.model.SeatingPlan;
import com.example.demo.service.SeatingPlanService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/seating-plan") 
public class SeatingPlanController {

    private final SeatingPlanService service;
    public SeatingPlanController(SeatingPlanService service){ this.service = service; }

  
@GetMapping("/{sessionId}")
public List<SeatingPlan> getPlansBySession(@PathVariable Long sessionId) {
    return service.getPlansBySession(sessionId);
}

    @GetMapping("/session/{sessionId}")
    public ResponseEntity<List<SeatingPlan>> list(Long sessionId){
        return ResponseEntity.ok(service.getPlansBySession(sessionId));
    }
}
