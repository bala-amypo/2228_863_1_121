package com.example.demo.service;

import com.example.demo.model.SeatingPlan;
import java.util.List;

public interface SeatingPlanService {
    SeatingPlan save(SeatingPlan plan);
    List<SeatingPlan> getAll();
}
