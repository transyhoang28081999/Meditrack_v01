package com.example.backend.services.interfaces;

import com.example.backend.controllers.controller_requests.CreateOrUpdatePrescriptionRequest;
import com.example.backend.controllers.controller_responses.FindFromUserResponse;
import com.example.backend.models.Prescription;

public interface PrescriptionService {
    FindFromUserResponse<Prescription> getAllPrescriptions(Long userID, int pageNo, int pageSize);
    Prescription getSinglePrescription(Long preID);

    String createPrescription(Long userID, CreateOrUpdatePrescriptionRequest createPrescriptionRequest);

    String updatePrescription(Long preID, CreateOrUpdatePrescriptionRequest updatePrescriptionRequest);

    String deletePrescription(Long preID);

}
