package com.example.unemploymentallowanceprogram.dto;

import java.util.Date;

public class ApprovedApplicationReport {
    private Integer applicationId;
    private Integer workerId;
    private String nationalId;
    private Date applicationDate;
    private String status;

    public ApprovedApplicationReport(Integer applicationId, Integer workerId, String nationalId, Date applicationDate, String status) {
        this.applicationId = applicationId;
        this.workerId = workerId;
        this.nationalId = nationalId;
        this.applicationDate = applicationDate;
        this.status = status;
    }

    // Getters
    public Integer getApplicationId() { return applicationId; }
    public Integer getWorkerId() { return workerId; }
    public String getNationalId() { return nationalId; }
    public Date getApplicationDate() { return applicationDate; }
    public String getStatus() { return status; }
}
