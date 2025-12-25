package com.example.demo.service.impl;

import com.example.demo.exception.ApiException;
import com.example.demo.model.*;
import com.example.demo.repository.*;
import com.example.demo.service.SeatingPlanService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;

@Service
public class SeatingPlanServiceImpl implements SeatingPlanService {

    private final ExamSessionRepository sessionRepo;
    private final SeatingPlanRepository planRepo;
    private final ExamRoomRepository roomRepo;

    public SeatingPlanServiceImpl(
            ExamSessionRepository sessionRepo,
            SeatingPlanRepository planRepo,
            ExamRoomRepository roomRepo) {
        this.sessionRepo = sessionRepo;
        this.planRepo = planRepo;
        this.roomRepo = roomRepo;
    }

    @Override
    public SeatingPlan generatePlan(Long sessionId) {
        ExamSession session = sessionRepo.findById(sessionId)
                .orElseThrow(() -> new ApiException("session not found"));

        List<ExamRoom> rooms = roomRepo.findAll();
        if (rooms.isEmpty())
            throw new ApiException("no room");

        int studentCount = session.getStudents().size();

        ExamRoom chosen = rooms.stream()
                .filter(r -> r.getCapacity() >= studentCount)
                .findFirst()
                .orElseThrow(() -> new ApiException("no room"));

        Map<String, String> arrangement = new LinkedHashMap<>();
        for (Student s : session.getStudents()) {
            arrangement.put(s.getRollNumber(), chosen.getRoomNumber());
        }

        SeatingPlan plan = new SeatingPlan();
        plan.setExamSession(session);
        plan.setRoom(chosen);
        plan.setGeneratedAt(LocalDateTime.now());

        try {
            plan.setArrangementJson(new ObjectMapper().writeValueAsString(arrangement));
        } catch (Exception e) {
            plan.setArrangementJson("{}");
        }

        return planRepo.save(plan);
    }

    @Override
    public SeatingPlan getPlan(Long id) {
        return planRepo.findById(id)
                .orElseThrow(() -> new ApiException("plan not found"));
    }

    @Override
    public List<SeatingPlan> getPlansBySession(Long sessionId) {
        return planRepo.findByExamSessionId(sessionId);
    }
}
