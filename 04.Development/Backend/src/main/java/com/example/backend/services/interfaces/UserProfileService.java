package com.example.backend.services.interfaces;


import com.example.backend.controllers.controller_requests.UpdateUserProfileRequest;
import com.example.backend.controllers.controller_requests.UserProfileRequest;
import com.example.backend.models.UserProfile;
import com.example.backend.securities.user.User;

import java.util.List;

public interface UserProfileService {
    List<UserProfile> getUserProfile(Long upID);

    void createUserProfile(User user);

    String updateUserProfile(Long userID, UpdateUserProfileRequest updateUserProfileRequest);

    String createUserProfile(Long userID, UserProfileRequest userProfileRequest);

    String deleteUserProfile(Long upID);
}
