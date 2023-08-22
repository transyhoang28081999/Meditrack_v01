package com.example.backend.services.classes;

import com.example.backend.controllers.controller_requests.UpdateMedicalRequest;
import com.example.backend.models.MedicalRecord;
import com.example.backend.models.UserProfile;
import com.example.backend.repositories.MedicalRecordRepository;
import com.example.backend.services.interfaces.MedicalRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MedicalRecordServiceImpl implements MedicalRecordService {
    @Autowired
    private MedicalRecordRepository medicalRecordRepository;

    @Override
    public MedicalRecord getMedicalRecord(Long userID) {
        return medicalRecordRepository.findByUserID(userID);
    }

    @Override
    public void createMedicalRecord(UserProfile userProfile) {
        MedicalRecord medicalRecord = new MedicalRecord();
        medicalRecord.setUserProfile(userProfile);
        medicalRecordRepository.save(medicalRecord);
    }

    @Override
    public String updateMedicalRecord(Long userID, UpdateMedicalRequest updateMedicalRequest) {
        MedicalRecord medicalRecord = medicalRecordRepository.findByUserID(userID);
        medicalRecord.setMedHistory(updateMedicalRequest.getMedHistory());
        medicalRecordRepository.save(medicalRecord);
        return "Update successfully!";
    }
}
