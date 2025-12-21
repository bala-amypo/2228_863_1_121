package com.example.demo.service;

import com.example.demo.exception.ApiException;
import com.example.demo.model.ExamRoom;
import com.example.demo.repository.ExamRoomRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExamRoomServiceImpl implements ExamRoomService {

    private final ExamRoomRepository repo;

    public ExamRoomServiceImpl(ExamRoomRepository repo) {
        this.repo = repo;
    }

    @Override
    public ExamRoom add(ExamRoom r) {
        if (r.getRows() <= 0 || r.getColumns() <= 0)
            throw new ApiException("invalid rows or columns");

        if (repo.findByRoomNumber(r.getRoomNumber()).isPresent())
            throw new ApiException("exists");

        r.ensureCapacityMatches();
        return repo.save(r);
    }

    @Override
    public List<ExamRoom> all() {
        return repo.findAll();
    }
}
