package com.example.unemploymentallowanceprogram.service;

import com.example.unemploymentallowanceprogram.entity.Worker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.example.unemploymentallowanceprogram.repository.WorkerRepository;

@Service
public class WorkerService {
    private final WorkerRepository workerRepository;

    @Autowired // Constructor injection is preferred over field injection
    public WorkerService(WorkerRepository workerRepository) {
        this.workerRepository = workerRepository;
    }

    @Transactional
    public Worker saveWorker(Worker worker) {
        // Business Rule: Check for duplicate National ID
        if (workerRepository.findByNationalIdNumber(worker.getNationalIdNumber()).isPresent()) {
            throw new IllegalArgumentException("A worker with this National ID already exists.");
        }

        // Save and return the persisted worker (with generated workerID)
        return workerRepository.save(worker);
    }
}
