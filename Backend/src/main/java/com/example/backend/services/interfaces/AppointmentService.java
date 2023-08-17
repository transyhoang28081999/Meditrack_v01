package com.example.backend.services.interfaces;

import com.example.backend.controllers.controller_requests.CreateAppointmentRequest;
import com.example.backend.controllers.controller_responses.AppointmentFindFromUserResponse;
import com.example.backend.enums.AppointmentStatusEnum;
import com.example.backend.models.Appointment;

import java.util.List;

public interface AppointmentService {
    AppointmentFindFromUserResponse getAllAppointments(Long userID, int pageNo, int pageSize, AppointmentStatusEnum appointmentStatus);

    String createAppointment(Long userID, CreateAppointmentRequest createAppointmentRequest);

    String updateAppointment(Long appointmentID, CreateAppointmentRequest createAppointmentRequest);

    String cancelAppointment(Long appointmentID);

    String postponeAppointment(Long appointmentID);
}
