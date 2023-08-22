package com.example.backend.repositories;

import com.example.backend.models.Prescription;
import com.example.backend.models.TakeMedicine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TakeMedicineRepository extends JpaRepository<TakeMedicine, Long> {

}