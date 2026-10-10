package com.example.unemploymentallowanceprogram.service;

import com.example.unemploymentallowanceprogram.dto.ApprovedApplicationReport;
import com.example.unemploymentallowanceprogram.entity.Application;
import com.example.unemploymentallowanceprogram.entity.Worker;
import com.example.unemploymentallowanceprogram.repository.ApplicationRepository;
import com.example.unemploymentallowanceprogram.repository.WorkerRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class ApplicationService {
    private final ApplicationRepository applicationRepository;
    private final WorkerRepository workerRepository;

    public ApplicationService(ApplicationRepository applicationRepository, WorkerRepository workerRepository) {
        this.applicationRepository = applicationRepository;
        this.workerRepository = workerRepository;
    }

    @Transactional
    public Application submitApplication(Application application) {
        if (application.getWorkerId() == null || !workerRepository.existsById(application.getWorkerId())) {
            throw new IllegalArgumentException("Invalid Submission: The selected worker does not exist in our registry.");
        }
        application.setApplicationDate(new Date());
        application.setStatus("PENDING");

        return applicationRepository.save(application);
    }


    public List<Application> getAllApplications() {
        return applicationRepository.findAll();
    }

    @Transactional
    public void approveApplication(Integer applicationId) {
        Application app = applicationRepository.findById(applicationId)
                .orElseThrow(() -> new IllegalArgumentException("Application not found"));
        app.setStatus("APPROVED");
        applicationRepository.save(app);
    }

    @Transactional
    public void rejectApplication(Integer applicationId) {
        Application app = applicationRepository.findById(applicationId)
                .orElseThrow(() -> new IllegalArgumentException("Application not found"));
        app.setStatus("REJECTED");
        applicationRepository.save(app);
    }

    public List<ApprovedApplicationReport> getApprovedApplicationsReport() {
        List<Application> allApps = applicationRepository.findAll();
        List<ApprovedApplicationReport> reportList = new ArrayList<>();

        for (Application app : allApps) {
            if ("APPROVED".equalsIgnoreCase(app.getStatus())) {
                String nationalId = "N/A";
                if (app.getWorkerId() != null) {
                    nationalId = workerRepository.findById(app.getWorkerId())
                            .map(Worker::getNationalIdNumber)
                            .orElse("Unknown");
                }

                reportList.add(new ApprovedApplicationReport(
                        app.getApplicationID(),
                        app.getWorkerId(),
                        nationalId,
                        app.getApplicationDate(),
                        app.getStatus()
                ));
            }
        }
        return reportList;
    }
}
