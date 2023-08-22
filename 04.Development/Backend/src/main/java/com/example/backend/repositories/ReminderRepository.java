package com.example.backend.repositories;

import com.example.backend.models.Reminder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ReminderRepository extends JpaRepository<Reminder, Long> {
    @Query("""
        select r from Reminder r, RemindAppointment ra, Appointment a
        where a.userProfile.upID = :upID
            and a.appID = ra.appointment.appID
            and ra.remID = r.remID
        order by r.remDatetime desc
    """)
    List<Reminder> findAllByUserProfileIDForRemindAppointment(Long upID);

    @Query("""
        select r from Reminder r, TakeMedicine tm, Prescription p
        where p.userProfile.upID = :upID
            and p.preID = tm.prescription.preID
            and tm.remID = r.remID
        order by r.remDatetime desc
    """)
    List<Reminder> findAllByUserProfileIDForTakeMedicine(Long upID);

    @Query("""
        select r from Reminder r, Other o
        where o.userProfile.upID = :upID
            and o.remID = r.remID
        order by r.remDatetime desc
    """)
    List<Reminder> findAllByUserProfileIDForOther(Long upID);
    @Query(" select r from Reminder r where r.remID =:remID")
    Optional<Reminder> findByRemID(Long remID);
}
