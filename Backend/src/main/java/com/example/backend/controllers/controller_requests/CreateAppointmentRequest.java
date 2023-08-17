package com.example.backend.controllers.controller_requests;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateAppointmentRequest {
    private String appAddress;
    // Format [dd/mm/yyyy hh-mm-ss]
    private String appDatetime;
    private String appInstitute;
    private String appDescription;
    private String appSpecialization;
    private String appDoctorName;
    private String appStatus;
}
