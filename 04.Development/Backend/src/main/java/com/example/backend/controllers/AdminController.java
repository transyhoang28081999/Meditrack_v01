package com.example.backend.controllers;

import com.example.backend.controllers.controller_responses.AdminFindUsersResponse;
import com.example.backend.securities.user.User;
import com.example.backend.securities.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1.0/admin")
public class AdminController {
    @Autowired
    private UserService userService;
    @GetMapping("find-users-by")
    public AdminFindUsersResponse getAllUsers(@RequestParam String partUserAccount, @RequestParam String partUserEmail, @RequestParam int pageNo, @RequestParam int pageSize){
        return userService.getAllUsers(partUserAccount, partUserEmail, pageNo, pageSize);

    }
    @GetMapping("find-user-by-id/{userID}")
    public User getSingleUser(@PathVariable Long userID) {
        return userService.getSingleUser(userID);
    }
    @PutMapping("change-password/{userID}")
    public String changePassword(@PathVariable Long userID, @RequestParam String newUserPassword){
        return userService.changePassword(userID, newUserPassword);
    }
    @PutMapping("activate-account/{userID}")
    public String activateAccount(@PathVariable Long userID){
        return  userService.activateAccount(userID);
    }
}
