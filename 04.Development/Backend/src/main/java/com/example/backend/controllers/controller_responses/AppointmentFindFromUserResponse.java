package com.example.backend.controllers.controller_responses;

import com.example.backend.models.Appointment;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AppointmentFindFromUserResponse {
    private List<Appointment> appointments;
    private int currentPage;
    private int pageSize;
    private int totalPages;
    private int totalAppointments;
}
