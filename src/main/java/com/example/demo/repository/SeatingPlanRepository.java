package com.example.demo.repository;

import com.example.demo.model.SeatingPlan;
import java.util.*;

public interface SeatingPlanRepository {
    SeatingPlan save(SeatingPlan p);
    Optional<SeatingPlan> findById(Long id);
    List<SeatingPlan> findByExamSessionId(Long sessionId);
}
