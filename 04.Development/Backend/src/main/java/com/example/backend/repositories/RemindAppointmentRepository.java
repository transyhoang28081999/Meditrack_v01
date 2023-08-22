package com.example.backend.repositories;

import com.example.backend.models.Appointment;
import com.example.backend.models.RemindAppointment;
import com.example.backend.securities.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RemindAppointmentRepository extends JpaRepository<RemindAppointment, Long> {
}