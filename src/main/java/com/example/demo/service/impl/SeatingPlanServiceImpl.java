package com.example.demo.service.impl;

import com.example.demo.exception.ApiException;
import com.example.demo.model.ExamRoom;
import com.example.demo.model.ExamSession;
import com.example.demo.model.SeatingPlan;
import com.example.demo.model.Student;
import com.example.demo.repository.ExamRoomRepository;
import com.example.demo.repository.ExamSessionRepository;
import com.example.demo.repository.SeatingPlanRepository;
import com.example.demo.service.SeatingPlanService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Service
public class SeatingPlanServiceImpl implements SeatingPlanService {

    private final ExamSessionRepository sessionRepo;
    private final SeatingPlanRepository planRepo;
    private final ExamRoomRepository roomRepo;

    public SeatingPlanServiceImpl(ExamSessionRepository sessionRepo,
                                  SeatingPlanRepository planRepo,
                                  ExamRoomRepository roomRepo) {
        this.sessionRepo = sessionRepo;
        this.planRepo = planRepo;
        this.roomRepo = roomRepo;
    }

    @Override
    public SeatingPlan generatePlan(long sessionId) {

        ExamSession session = sessionRepo.findById(sessionId)
                .orElseThrow(() -> new ApiException("session not found"));

        List<ExamRoom> rooms = roomRepo.findAll();
        if (rooms.isEmpty())
            throw new ApiException("no room");

        ExamRoom room = rooms.get(0);

        Map<String, String> arrangement = new LinkedHashMap<>();
        int seat = 1;
        for (Student s : session.getStudents()) {
            arrangement.put("Seat-" + seat++, s.getRollNumber());
        }

        try {
            SeatingPlan plan = new SeatingPlan();
            plan.setExamSession(session);
            plan.setRoom(room);
            plan.setGeneratedAt(LocalDateTime.now());
            plan.setArrangementJson(new ObjectMapper().writeValueAsString(arrangement));
            return planRepo.save(plan);
        } catch (Exception e) {
            throw new ApiException("plan error");
        }
    }

    @Override
    public SeatingPlan getPlan(long id) {
        return planRepo.findById(id)
                .orElseThrow(() -> new ApiException("plan not found"));
    }

    @Override
    public List<SeatingPlan> getPlansBySession(long sessionId) {
        return planRepo.findByExamSessionId(sessionId);
    }
}
