package com.example.demo.service.impl;

import com.example.demo.model.SeatingPlan;
import com.example.demo.service.SeatingPlanService;
import org.springframework.stereotype.Service;

@Service
public class SeatingPlanServiceImpl implements SeatingPlanService {

    @Override
    public SeatingPlan generatePlan(Long sessionId) {
        return new SeatingPlan();
    }

    @Override
    public SeatingPlan getPlan(Long planId) {
        return null;
    }
}
