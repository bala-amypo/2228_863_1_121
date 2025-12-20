package com.example.demo.service;

import com.example.demo.model.SeatingPlan;
import java.util.List;

public interface SeatingPlanService {

    SeatingPlan generatePlan(Long examSessionId);

    SeatingPlan getPlan(Long id);

    List<SeatingPlan> getPlansBySession(Long examSessionId);
}
