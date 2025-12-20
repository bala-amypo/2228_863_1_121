package com.example.demo.service;

import com.example.demo.exception.ApiException;
import com.example.demo.model.*;
import com.example.demo.repository.*;

import java.util.List;

public class SeatingPlanServiceImpl implements SeatingPlanService {

    private final ExamSessionRepository examSessionRepository;
    private final SeatingPlanRepository seatingPlanRepository;
    private final ExamRoomRepository examRoomRepository;

    public SeatingPlanServiceImpl(ExamSessionRepository examSessionRepository,
                                  SeatingPlanRepository seatingPlanRepository,
                                  ExamRoomRepository examRoomRepository) {
        this.examSessionRepository = examSessionRepository;
        this.seatingPlanRepository = seatingPlanRepository;
        this.examRoomRepository = examRoomRepository;
    }

    @Override
    public SeatingPlan generatePlan(Long sessionId) {

        ExamSession session = examSessionRepository.findById(sessionId)
                .orElseThrow(() -> new ApiException("session not found"));

        int studentCount = session.getStudents().size();

        List<ExamRoom> rooms =
                examRoomRepository.findByCapacityGreaterThanEqual(studentCount);

        if (rooms.isEmpty()) {
            throw new ApiException("no room");
        }

        ExamRoom room = rooms.get(0);

        StringBuilder json = new StringBuilder("{");
        int seat = 1;
        for (Student s : session.getStudents()) {
            json.append("\"seat").append(seat++)
                .append("\":\"")
                .append(s.getRollNumber()).append("\",");
        }
        if (json.charAt(json.length() - 1) == ',') {
            json.deleteCharAt(json.length() - 1);
        }
        json.append("}");

        SeatingPlan plan = new SeatingPlan();
        plan.setExamSession(session);
        plan.setRoom(room);
        plan.setArrangementJson(json.toString());

        return seatingPlanRepository.save(plan);
    }

    @Override
    public SeatingPlan getPlan(Long planId) {
        return seatingPlanRepository.findById(planId)
                .orElseThrow(() -> new ApiException("plan not found"));
    }

    @Override
    public List<SeatingPlan> getPlansBySession(Long sessionId) {
        return seatingPlanRepository.findByExamSessionId(sessionId);
    }
}
