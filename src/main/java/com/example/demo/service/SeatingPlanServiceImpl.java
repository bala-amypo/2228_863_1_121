package com.example.demo.service;

import com.example.demo.model.SeatingPlan;
import com.example.demo.repository.SeatingPlanRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class SeatingPlanServiceImpl implements SeatingPlanService {

    private final SeatingPlanRepository repository;

    public SeatingPlanServiceImpl(SeatingPlanRepository repository) {
        this.repository = repository;
    }

    @Override
    public SeatingPlan generatePlan(Long examSessionId) {
        SeatingPlan plan = new SeatingPlan();
        plan.setGeneratedAt(LocalDateTime.now());
        plan.setArrangementJson("{}");
        return repository.save(plan);
    }

    @Override
    public SeatingPlan getPlan(Long id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public List<SeatingPlan> getPlansBySession(Long examSessionId) {
        return repository.findAll();
    }
}
