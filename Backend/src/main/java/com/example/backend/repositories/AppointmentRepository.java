package com.example.backend.repositories;

import com.example.backend.enums.AppointmentStatusEnum;
import com.example.backend.models.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
    @Query("""
        select a from Appointment a
        where a.user.userID = :userID
            and a.appStatus = :appointmentStatus
        order by a.appDatetime desc
    """)
    List<Appointment> findAllAppointmentOfThis(Long userID,@Param("appointmentStatus") AppointmentStatusEnum appointmentStatus);
    @Query("""
        select a from Appointment a
        where a.user.userID = :userID
        order by a.appDatetime desc
    """)
    List<Appointment> findAllAppointmentOfThis(Long userID);
}
