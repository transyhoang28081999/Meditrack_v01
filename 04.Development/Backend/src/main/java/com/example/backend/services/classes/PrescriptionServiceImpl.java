package com.example.backend.services.classes;

import com.example.backend.controllers.controller_requests.CreateOrUpdatePrescriptionRequest;
import com.example.backend.controllers.controller_responses.FindFromUserResponse;
import com.example.backend.generics.Pagination;
import com.example.backend.models.Prescription;
import com.example.backend.models.UserProfile;
import com.example.backend.repositories.PrescriptionRepository;
import com.example.backend.repositories.UserProfileRespository;
import com.example.backend.securities.user.User;
import com.example.backend.securities.user.UserRepository;
import com.example.backend.services.interfaces.PrescriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PrescriptionServiceImpl implements PrescriptionService {
    @Autowired
    private PrescriptionRepository prescriptionRepository;
    @Autowired
    private UserProfileRespository userProfileRespository;
    @Override
    public FindFromUserResponse<Prescription> getAllPrescriptions(Long upID, int pageNo, int pageSize) {
        List<Prescription> allPrescriptions = prescriptionRepository.findAllPrescriptions(upID);
        //Phan trang
        Pagination<Prescription> pagination = new Pagination<>();
        List<Prescription> prescriptions = pagination.pagination(allPrescriptions, pageNo, pageSize);
        return new FindFromUserResponse<Prescription>(
                prescriptions,
                pageNo,
                pageSize,
                Math.floorDiv(allPrescriptions.size(), pageSize) + 1,
                allPrescriptions.size()
        );
    }

    @Override
    public Prescription getSinglePrescription(Long preID) {
        return prescriptionRepository.findById(preID).orElseThrow();
    }

    @Override
    public String createPrescription(Long upID, CreateOrUpdatePrescriptionRequest createPrescriptionRequest) {
        UserProfile userProfile = userProfileRespository.findByUpID(upID).orElseThrow();

        Prescription prescription = new Prescription();
        prescription.setPreMedicine(createPrescriptionRequest.getPreMedicine());
        prescription.setPreDosage(createPrescriptionRequest.getPreDosage());
        prescription.setPreDuration(createPrescriptionRequest.getPreDuration());
        prescription.setPreNotes(createPrescriptionRequest.getPreNotes());
        prescription.setPreDoctor(createPrescriptionRequest.getPreDoctor());
        prescription.setUserProfile(userProfile);
        prescriptionRepository.save(prescription);
        return "Create a prescription successfully!";
    }

    @Override
    public String updatePrescription(Long preID, CreateOrUpdatePrescriptionRequest updatePrescriptionRequest) {
        Prescription prescription = prescriptionRepository.findById(preID).orElseThrow();

        prescription.setPreMedicine(updatePrescriptionRequest.getPreMedicine());
        prescription.setPreDosage(updatePrescriptionRequest.getPreDosage());
        prescription.setPreDuration(updatePrescriptionRequest.getPreDuration());
        prescription.setPreNotes(updatePrescriptionRequest.getPreNotes());
        prescription.setPreDoctor(updatePrescriptionRequest.getPreDoctor());

        prescriptionRepository.save(prescription);

        return "Update this prescription successfully!";
    }

    @Override
    public String deletePrescription(Long preID) {
        prescriptionRepository.deleteById(preID);
        return "Delete this prescription successfully!";
    }
}
