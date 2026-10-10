package com.example.unemploymentallowanceprogram.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;


@Entity
@Table(name = "worker")
public class Worker {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer workerID;
    @NotBlank(message ="National Id Can not be Blank")
    private String nationalIdNumber;
    @NotBlank(message ="Name Can not be Blank")
    private String workerName;
    @NotBlank(message ="Mobile Number Can not be Blank")
    @Pattern(regexp = "^\\d{11}$", message = "Mobile number must be exactly 11 numeric digits.")
    private String mobileNUmber;

    public Integer getWorkerID() {
        return workerID;
    }

    public void setWorkerID(Integer workerID) {
        this.workerID = workerID;
    }

    public String getNationalIdNumber() {
        return nationalIdNumber;
    }

    public void setNationalIdNumber(String nationalIdNumber) {
        this.nationalIdNumber = nationalIdNumber;
    }

    public String getWorkerName() {
        return workerName;
    }

    public void setWorkerName(String workerName) {
        this.workerName = workerName;
    }

    public String getMobileNUmber() {
        return mobileNUmber;
    }

    public void setMobileNUmber(String mobileNUmber) {
        this.mobileNUmber = mobileNUmber;
    }
}
