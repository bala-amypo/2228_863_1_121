 package com.example.demo.service.impl;

import com.example.demo.repository.ExamRoomRepository;
import com.example.demo.repository.ExamSessionRepository;
import com.example.demo.repository.SeatingPlanRepository;
import com.example.demo.service.SeatingPlanService;

public class SeatingPlanServiceImpl extends SeatingPlanService {
    public SeatingPlanServiceImpl(ExamSessionRepository sessionRepo, SeatingPlanRepository planRepo, ExamRoomRepository roomRepo){
        super(sessionRepo, planRepo, roomRepo);
    }
}
