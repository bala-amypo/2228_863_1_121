package com.example.demo.service;

import com.example.demo.model.SeatingPlan;
import com.example.demo.repository.SeatingPlanRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class SeatingPlanServiceImpl implements SeatingPlanService {

    private final SeatingPlanRepository seatingPlanRepository;

    public SeatingPlanServiceImpl(SeatingPlanRepository seatingPlanRepository) {
        this.seatingPlanRepository = seatingPlanRepository;
    }

    @Override
    public SeatingPlan generatePlan(Long examSessionId) {
        SeatingPlan plan = new SeatingPlan();
        plan.setGeneratedAt(LocalDateTime.now());
        plan.setArrangementJson("{}"); // placeholder
        return seatingPlanRepository.save(plan);
    }

    @Override
    public SeatingPlan getPlan(Long id) {
        return seatingPlanRepository.findById(id).orElse(null);
    }

    @Override
    public List<SeatingPlan> getPlansBySession(Long examSessionId) {
        return seatingPlanRepository.findAll();
    }
}
