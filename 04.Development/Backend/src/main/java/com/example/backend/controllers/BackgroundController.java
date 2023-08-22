package com.example.backend.controllers;

import com.example.backend.controllers.controller_requests.BackgroundRequest;
import com.example.backend.controllers.controller_responses.FindFromUserResponse;
import com.example.backend.models.Background;
import com.example.backend.services.interfaces.BackgroundService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("api/v1.0/user/background/{userID}")
public class BackgroundController {
    @Autowired
    private BackgroundService backgroundService;
    @GetMapping
    public FindFromUserResponse<Background> getAllBackgrounds(@PathVariable Long userID, @RequestParam int pageNo, @RequestParam int pageSize) {
        return backgroundService.getAllBackgrounds(userID, pageNo, pageSize);
    }
    @GetMapping("{bgDate}")
    public Background getSingleBackground(@PathVariable Long userID, @PathVariable LocalDate bgDate) {
        return backgroundService.getSingleBackground(userID, bgDate);
    }
    @PostMapping
    public String createBackground(@PathVariable Long userID, @RequestBody BackgroundRequest backgroundRequest) {
        return backgroundService.createBackground(userID, backgroundRequest);
    }
    @PutMapping
    public String updateBackground(@RequestBody BackgroundRequest backgroundRequest) {
        return backgroundService.updateBackground(backgroundRequest);
    }
}
