package com.example.backend.services.interfaces;

import com.example.backend.controllers.controller_requests.BackgroundRequest;
import com.example.backend.controllers.controller_responses.FindFromUserResponse;
import com.example.backend.models.Background;

import java.time.LocalDate;

public interface BackgroundService {
    FindFromUserResponse<Background> getAllBackgrounds(Long userID, int pageNo, int pageSize);

    Background getSingleBackground(Long userID, LocalDate bgDate);

    String createBackground(Long userID, BackgroundRequest backgroundRequest);

    String updateBackground(BackgroundRequest backgroundRequest);
}
