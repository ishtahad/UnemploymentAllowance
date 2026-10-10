package com.example.unemploymentallowanceprogram.controller;

import com.example.unemploymentallowanceprogram.entity.Application;
import com.example.unemploymentallowanceprogram.entity.Worker;
import com.example.unemploymentallowanceprogram.repository.WorkerRepository;
import com.example.unemploymentallowanceprogram.service.ApplicationService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/ui/applications")
public class ApplicationViewController {

    private final ApplicationService applicationService;
    private final WorkerRepository workerRepository;

    public ApplicationViewController(ApplicationService applicationService, WorkerRepository workerRepository) {
        this.applicationService = applicationService;
        this.workerRepository = workerRepository;
    }

    @GetMapping("/review")
    public String reviewDashboard(Model model) {
        model.addAttribute("applications", applicationService.getAllApplications());
        return "review-dashboard"; // Maps to review-dashboard.html
    }

    @PostMapping("/review/{id}/reject")
    public String handleRejection(@PathVariable("id") Integer id) {
        applicationService.rejectApplication(id);
        return "redirect:/ui/applications/review"; // Instantly reloads the dashboard with new records
    }

    @PostMapping("/review/{id}/approve")
    public String handleApproval(@PathVariable("id") Integer id) {
        applicationService.approveApplication(id);
        return "redirect:/ui/applications/review"; // Instantly reloads the dashboard with new records
    }

    @GetMapping("/create")
    public String showApplicationForm(Model model) {
        model.addAttribute("applicationData", new Application());
        List<Worker> workers = workerRepository.findAll();
        model.addAttribute("workersList", workers);
        return "create-application";
    }

    @PostMapping("/submit")
    public String processApplication(@ModelAttribute("applicationData") Application application, Model model) {
        try {
            Application savedApp = applicationService.submitApplication(application);
            model.addAttribute("successMessage", "Application submitted successfully! Tracking ID: " + savedApp.getApplicationID());
            model.addAttribute("applicationData", new Application());
        } catch (Exception e) {
            model.addAttribute("errorMessage", "Submission failed: " + e.getMessage());
            model.addAttribute("applicationData", application);
        }
        model.addAttribute("workersList", workerRepository.findAll());
        return "create-application";

    }

    @GetMapping("/report")
    public String showApprovedReport(Model model) {
        model.addAttribute("reportData", applicationService.getApprovedApplicationsReport());
        return "approved-report";
    }
}
