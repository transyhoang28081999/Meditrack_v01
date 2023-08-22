package com.example.backend.repositories;

import com.example.backend.enums.AppointmentStatusEnum;
import com.example.backend.models.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
    @Query("""
        select a from Appointment a where a.userProfile.upID = :upID and a.appStatus = :appointmentStatus order by a.appDatetime desc
    """)
    List<Appointment> findAllAppointmentOfThis(Long upID,@Param("appointmentStatus") AppointmentStatusEnum appointmentStatus);
    @Query("""
        select a from Appointment a where a.userProfile.upID = :upID order by a.appDatetime desc
    """)
    List<Appointment> findAllAppointmentOfThis(Long upID);
//    @Query("""
//        select a from Appointment a, User u
//        where u.userID =:userID
//            and u.userID = a.user.userID
//            and a.appID = :appID
//    """)
//    Optional<Appointment> findByUserIDAndAppointmentID(Long userID, Long appID);
}
