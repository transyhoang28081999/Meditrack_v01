package com.example.backend.services.interfaces;

import com.example.backend.controllers.controller_requests.UpdateMedicalRequest;
import com.example.backend.models.MedicalRecord;
import com.example.backend.models.UserProfile;

public interface MedicalRecordService {
    void createMedicalRecord(UserProfile userProfile);

    MedicalRecord getMedicalRecord(Long userID);

    String updateMedicalRecord(Long userID, UpdateMedicalRequest updateMedicalRequest);
}
