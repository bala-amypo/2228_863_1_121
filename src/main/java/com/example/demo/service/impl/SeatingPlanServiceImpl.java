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

        int students = session.getStudents().size();

        ExamRoom chosen = rooms.stream()
                .filter(r -> r.getCapacity() >= students)
                .findFirst()
                .orElseThrow(() -> new ApiException("no room"));

        Map<String, String> map = new LinkedHashMap<>();
        session.getStudents().forEach(s -> map.put(s.getRollNumber(), chosen.getRoomNumber()));

        SeatingPlan p = new SeatingPlan();
        p.setExamSession(session);
        p.setRoom(chosen);
        p.setGeneratedAt(LocalDateTime.now());

        try {
            p.setArrangementJson(new ObjectMapper().writeValueAsString(map));
        } catch (Exception e) {
            p.setArrangementJson("{}");
        }

        return planRepo.save(p);
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
