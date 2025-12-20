package com.example.demo.service;

import com.example.demo.model.SeatingPlan;
import com.example.demo.repository.SeatingPlanRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SeatingPlanServiceImpl implements SeatingPlanService {

    private final SeatingPlanRepository seatingPlanRepository;

    public SeatingPlanServiceImpl(SeatingPlanRepository seatingPlanRepository) {
        this.seatingPlanRepository = seatingPlanRepository;
    }

    @Override
    public SeatingPlan save(SeatingPlan plan) {
        return seatingPlanRepository.save(plan);
    }

    @Override
    public List<SeatingPlan> getAll() {
        return seatingPlanRepository.findAll();
    }
}
