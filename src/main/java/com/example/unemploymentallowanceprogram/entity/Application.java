package com.example.unemploymentallowanceprogram.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.Date;
@Entity
@Table(name = "application")
public class Application {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer applicationID;
    @NotNull
    private Integer workerId;
    private Date applicationDate;
    @NotBlank(message = "Can not be Blank")
    private String reasonForUnemployment;
    private String status;

    public Integer getApplicationID() {
        return applicationID;
    }

    public void setApplicationID(Integer applicationID) {
        this.applicationID = applicationID;
    }

    public Integer getWorkerId() {
        return workerId;
    }

    public void setWorkerId(Integer workerId) {
        this.workerId = workerId;
    }

    public Date getApplicationDate() {
        return applicationDate;
    }

    public void setApplicationDate(Date applicationDate) {
        this.applicationDate = applicationDate;
    }

    public String getReasonForUnemployment() {
        return reasonForUnemployment;
    }

    public void setReasonForUnemployment(String reasonForUnemployment) {
        this.reasonForUnemployment = reasonForUnemployment;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
