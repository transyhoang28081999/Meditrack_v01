package com.example.backend.repositories;

import com.example.backend.models.MedicalRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface MedicalRecordRepository extends JpaRepository<MedicalRecord, Long> {
    @Query("""
        select m from MedicalRecord m, UserProfile u
        where u.user.userID = :userID
            and u.upID = m.userProfile.upID
    """)
    MedicalRecord findByUserID(Long userID);
}
