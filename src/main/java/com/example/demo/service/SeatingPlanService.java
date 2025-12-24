package com.example.demo.service;

import com.example.demo.model.SeatingPlan;

public interface SeatingPlanService {

    SeatingPlan generatePlan(Long examSessionId, Long roomId);
}
