package com.example.unemploymentallowanceprogram.repository;

import com.example.unemploymentallowanceprogram.entity.Worker;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface WorkerRepository extends JpaRepository<Worker, Integer> {
    // Custom method to check if a national ID already exists
    Optional<Worker> findByNationalIdNumber(String nationalIdNumber);


}
