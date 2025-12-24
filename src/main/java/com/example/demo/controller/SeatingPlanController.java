package com.example.demo.controller;

import com.example.demo.model.SeatingPlan;
import com.example.demo.service.SeatingPlanService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/plans")
@Tag(name = "Seating Plans")
public class SeatingPlanController {

    private final SeatingPlanService seatingPlanService;

    public SeatingPlanController(SeatingPlanService seatingPlanService) {
        this.seatingPlanService = seatingPlanService;
    }

    @Operation(summary = "Generate seating plan for an exam session")
    @PostMapping("/generate/{sessionId}")
    public SeatingPlan generatePlan(@PathVariable Long sessionId) {
        return seatingPlanService.generatePlan(sessionId);
    }

    @Operation(summary = "Get seating plan by ID")
    @GetMapping("/{planId}")
    public SeatingPlan getPlan(@PathVariable Long planId) {
        return seatingPlanService.getPlan(planId);
    }

    @Operation(summary = "Get all seating plans for a session")
    @GetMapping("/session/{sessionId}")
    public List<SeatingPlan> getPlansBySession(@PathVariable Long sessionId) {
        return seatingPlanService.getPlansBySession(sessionId);
    }
}
