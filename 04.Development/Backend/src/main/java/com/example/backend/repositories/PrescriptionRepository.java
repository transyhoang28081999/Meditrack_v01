package com.example.backend.repositories;

import com.example.backend.models.Prescription;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PrescriptionRepository extends JpaRepository<Prescription, Long> {
    @Query("""
        select p from Prescription p
        where p.userProfile.upID = :upID
        order by p.preMedicine
    """)
    List<Prescription> findAllPrescriptions(Long upID);
}
