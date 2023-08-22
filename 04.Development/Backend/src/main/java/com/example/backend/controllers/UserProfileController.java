package com.example.backend.controllers;

import com.example.backend.controllers.controller_requests.UpdateUserProfileRequest;
import com.example.backend.controllers.controller_requests.UserProfileRequest;
import com.example.backend.models.UserProfile;
import com.example.backend.services.interfaces.UserProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1.0/user/user-profile/{userID}")
public class UserProfileController {
    @Autowired
    private UserProfileService userProfileService;
    @GetMapping
    public List<UserProfile> getUserProfile(@PathVariable Long userID) {
        return userProfileService.getUserProfile(userID);
    }
    @PostMapping
    public String createUserProfile(@PathVariable Long userID, @RequestBody UserProfileRequest userProfileRequest) {
        return userProfileService.createUserProfile(userID, userProfileRequest);
    }
    @PutMapping("{upID}")
    public String updateUserProfile(@PathVariable Long upID, @RequestBody UpdateUserProfileRequest updateUserProfileRequest) {
        return userProfileService.updateUserProfile(upID, updateUserProfileRequest);
    }
    @DeleteMapping("{upID}")
    public String deleteUserProfile(@PathVariable Long upID) {
        return userProfileService.deleteUserProfile(upID);
    }
}
