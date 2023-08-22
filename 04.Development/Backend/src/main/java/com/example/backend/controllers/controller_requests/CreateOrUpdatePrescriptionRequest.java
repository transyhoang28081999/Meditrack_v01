package com.example.backend.controllers.controller_requests;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateOrUpdatePrescriptionRequest {
    private String preMedicine;
    private Integer preDosage;
    private String preDuration;
    private String preNotes;
    private String preDoctor;
}
