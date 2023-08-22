package com.example.backend.controllers;

import com.example.backend.controllers.controller_requests.CreateAppointmentRequest;
import com.example.backend.controllers.controller_responses.FindFromUserResponse;
import com.example.backend.enums.AppointmentStatusEnum;
import com.example.backend.models.Appointment;
import com.example.backend.services.interfaces.AppointmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;


@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1.0/user/appointment/{userID}")
public class AppointmentController {
    private final AppointmentService appointmentService;

    //User lấy thông tin tất cả cuộc hẹn
    @GetMapping("get-all/{upID}")
    public FindFromUserResponse<Appointment> getAllAppointments(@PathVariable Long upID,
                                                                @RequestParam int pageNo,
                                                                @RequestParam int pageSize,
                                                                @RequestParam(required = false) AppointmentStatusEnum appointmentStatus) {
        return appointmentService.getAllAppointments(upID, pageNo, pageSize, appointmentStatus);
    }
    @GetMapping("get-single/{appointmentID}")
    public Appointment getSingleAppointment(@PathVariable Long appointmentID) {
        return appointmentService.getSingleAppointment(appointmentID);
    }
    @PostMapping("create-appointment")
    public String createAppointment(@PathVariable Long userID, @RequestBody CreateAppointmentRequest createAppointmentRequest) {
        return appointmentService.createAppointment(userID, createAppointmentRequest);
    }

    @PutMapping("update-appointment/{appointmentID}")
    public String updateAppointment(@PathVariable Long appointmentID, @RequestBody CreateAppointmentRequest createAppointmentRequest) {
        return appointmentService.updateAppointment(appointmentID, createAppointmentRequest);
    }

    @PutMapping("cancel-appointment/{appointmentID}")
    public String cancelAppointment(@PathVariable Long appointmentID){
        return appointmentService.cancelAppointment(appointmentID);
    }
    @PutMapping("postpone-appointment/{appointmentID}")
    public String postponeAppointment(@PathVariable Long appointmentID) {
        return appointmentService.postponeAppointment(appointmentID);
    }
}
