package com.example.unemploymentallowanceprogram.controller;

import com.example.unemploymentallowanceprogram.entity.Worker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.unemploymentallowanceprogram.service.WorkerService;

@RestController
@RequestMapping("/api/workers")
public class WorkerController {
    @Autowired
    private WorkerService workerService;

    @PostMapping("/save")
    public ResponseEntity<String> createWorker(@RequestBody Worker worker) {
        workerService.saveWorker(worker);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body("Worker created successfully with ID: " + worker.getWorkerID());
    }
}
