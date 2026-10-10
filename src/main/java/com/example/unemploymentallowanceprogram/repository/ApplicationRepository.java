package com.example.unemploymentallowanceprogram.repository;

import com.example.unemploymentallowanceprogram.entity.Application;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ApplicationRepository extends JpaRepository<Application, Integer> {
    // Custom query to find all applications submitted by a specific worker string ID
    List<Application> findByWorkerId(Integer workerId);
}
