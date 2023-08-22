package com.example.backend.services.classes;

import com.example.backend.controllers.controller_requests.UpdateUserProfileRequest;
import com.example.backend.controllers.controller_requests.UserProfileRequest;
import com.example.backend.enums.UserProfileEnum;
import com.example.backend.models.UserProfile;
import com.example.backend.repositories.UserProfileRespository;
import com.example.backend.securities.user.User;
import com.example.backend.securities.user.UserRepository;
import com.example.backend.services.interfaces.UserProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserProfileServiceImpl implements UserProfileService {
    @Autowired
    private UserProfileRespository userProfileRespository;
    @Autowired
    private UserRepository userRepository;
    @Override
    public List<UserProfile> getUserProfile(Long userID) {
        return userProfileRespository.findByUserID(userID);
    }

    @Override
    public void createUserProfile(User user) {
        UserProfile userProfile = new UserProfile();
        userProfile.setUser(user);
        userProfile.setUpRole(UserProfileEnum.MAIN);
        userProfileRespository.save(userProfile);
    }

    @Override
    public String createUserProfile(Long userID, UserProfileRequest userProfileRequest) {
        User user = userRepository.findByUserID(userID).orElseThrow();
        UserProfile userProfile = new UserProfile();
        userProfile.setUpName(userProfileRequest.getUpName());
        userProfile.setUpDoB(userProfileRequest.getUpDoB());
        userProfile.setUpGender(userProfileRequest.getUpGender());
        userProfile.setUpPhone(userProfileRequest.getUpPhone());
        userProfile.setUpIDCode(userProfileRequest.getUpIDCode());

        userProfile.setUser(user);
        userProfile.setUpRole(UserProfileEnum.BRANCH);
        userProfileRespository.save(userProfile);
        return "Create Successfully!";
    }

    @Override
    public String updateUserProfile(Long upID, UpdateUserProfileRequest updateUserProfileRequest) {
        UserProfile userProfile = userProfileRespository.findByUpID(upID).orElseThrow();

        userProfile.setUpName(updateUserProfileRequest.getName());
        userProfile.setUpDoB(updateUserProfileRequest.getDob());
        userProfile.setUpPhone(updateUserProfileRequest.getPhone());
        userProfile.setUpGender(updateUserProfileRequest.getGender());
        userProfile.setUpIDCode(updateUserProfileRequest.getIdcode());

        userProfileRespository.save(userProfile);
        return "Update successfully";
    }

    @Override
    public String deleteUserProfile(Long upID) {
        UserProfile userProfile = userProfileRespository.findByUpID(upID).orElseThrow();
        if(userProfile.getUpRole() == UserProfileEnum.MAIN) return "Can not delete this profile";

        userProfileRespository.deleteById(upID);
        return "delete successfully!";
    }
}
