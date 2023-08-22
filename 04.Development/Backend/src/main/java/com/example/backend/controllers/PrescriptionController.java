package com.example.backend.controllers;

import com.example.backend.controllers.controller_requests.CreateOrUpdatePrescriptionRequest;
import com.example.backend.controllers.controller_responses.FindFromUserResponse;
import com.example.backend.models.Prescription;
import com.example.backend.services.interfaces.PrescriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1.0/user/prescription/{userID}")
public class PrescriptionController {
    @Autowired
    private PrescriptionService prescriptionService;
    @GetMapping("get-all/{upID}")
    public FindFromUserResponse<Prescription> getAllPrescriptions(
            @PathVariable Long upID,
            @RequestParam int pageNo,
            @RequestParam int pageSize
    ){
        return prescriptionService.getAllPrescriptions(upID, pageNo, pageSize);
    }
    @GetMapping("get-single/{preID}")
    public Prescription getSinglePrescription(@PathVariable Long preID) {
        return prescriptionService.getSinglePrescription(preID);
    }
    @PostMapping("{upID}")
    public String createPrescription(@PathVariable Long upID, @RequestBody CreateOrUpdatePrescriptionRequest createPrescriptionRequest) {
        return prescriptionService.createPrescription(upID, createPrescriptionRequest);
    }
    @PutMapping("{preID}")
    public String updatePrescription(@PathVariable Long preID, @RequestBody CreateOrUpdatePrescriptionRequest updatePrescriptionRequest) {
        return prescriptionService.updatePrescription(preID, updatePrescriptionRequest);
    }
    @DeleteMapping("{preID}")
    public String deletePrescription(@PathVariable Long preID) {
        return prescriptionService.deletePrescription(preID);
    }
}
