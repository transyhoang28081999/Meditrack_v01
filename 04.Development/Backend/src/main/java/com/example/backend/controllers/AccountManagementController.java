package com.example.backend.controllers;

import com.example.backend.controllers.controller_requests.ChangePasswordRequest;
import com.example.backend.securities.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1.0/account-management")
public class AccountManagementController {
    @Autowired
    private UserService userService;
    @PutMapping("change-password/{userID}")
    public String changePassword(@PathVariable Long userID , @RequestBody ChangePasswordRequest changePasswordRequest){
        return userService.changePassword(userID, changePasswordRequest);
    }
    @PutMapping("update-email/{userID}")
    public String updateEmail(@PathVariable Long userID, @RequestParam String newUserEmail){
        return userService.updateEmail(userID, newUserEmail);
    }
    @DeleteMapping("delete-account/{userID}")
    public String deleteAccount(@PathVariable Long userID){
        return userService.deleteAccount(userID);
    }
}
