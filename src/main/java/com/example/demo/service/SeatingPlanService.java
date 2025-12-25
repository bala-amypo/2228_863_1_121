package com.example.demo.service;

import com.example.demo.model.SeatingPlan;
import java.util.List;

public interface SeatingPlanService {
    List<SeatingPlan> getPlansBySession(Long sessionId);
}
