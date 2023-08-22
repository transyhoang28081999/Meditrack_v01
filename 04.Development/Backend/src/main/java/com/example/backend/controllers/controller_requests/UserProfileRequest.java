package com.example.backend.controllers.controller_requests;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserProfileRequest {
    private String upName;
    private LocalDate upDoB;
    private String upPhone;
    private String upGender;
    private String upIDCode;
}
