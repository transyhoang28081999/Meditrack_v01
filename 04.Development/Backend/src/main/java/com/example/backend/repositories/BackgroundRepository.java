package com.example.backend.repositories;

import com.example.backend.models.Background;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface BackgroundRepository extends JpaRepository<Background, Long> {
    @Query("""
        select b from Background b, MedicalRecord m, UserProfile u
        where u.user.userID = :userID
            and u.upID = m.userProfile.upID
            and m.medID = b.medicalRecord.medID
        order by b.bgDate desc
    """)
    List<Background> findAllByUserID(Long userID);

    @Query("""
        select b from Background b, MedicalRecord m, UserProfile u
        where u.user.userID = :userID
            and b.bgDate = :bgDate
            and u.upID = m.userProfile.upID
            and m.medID = b.medicalRecord.medID
    """)
    Optional<Background> findByUserIDAndBackgroundDate(Long userID, LocalDate bgDate);

    @Query("select b from Background b")
    List<Background> findBackgroundByAll();

    @Query("""
        select b from Background b where b.bgDate = :now
    """)
    Optional<Background> findBackgroundByBgDate(LocalDate now);
}
