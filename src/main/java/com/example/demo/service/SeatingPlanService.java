package com.example.demo.service;

import com.example.demo.model.SeatingPlan;
import java.util.List;

public interface SeatingPlanService {

    SeatingPlan generatePlan(long sessionId);

    SeatingPlan getPlan(long id);

    List<SeatingPlan> getPlansBySession(long sessionId);
}
