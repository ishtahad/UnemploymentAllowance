package com.example.unemploymentallowanceprogram.controller;

import com.example.unemploymentallowanceprogram.entity.Worker;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import com.example.unemploymentallowanceprogram.service.WorkerService;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/workers") 
public class WorkerViewController {

    private final WorkerService workerService;

    public WorkerViewController(WorkerService workerService) {
        this.workerService = workerService;
    }

    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("worker", new Worker());
        return "create-worker";
    }

    @PostMapping("/create")
    public String registerWorker(@ModelAttribute("worker") Worker worker, Model model) {
        try {
            Worker saved = workerService.saveWorker(worker);
            model.addAttribute("successMessage", "Worker created successfully with ID: " + saved.getWorkerID());
            model.addAttribute("worker", new Worker());
        } catch (Exception e) {
            model.addAttribute("errorMessage", "Failed to save: " + e.getMessage());
            model.addAttribute("worker", worker);
        }
        return "create-worker";
    }
}
