package com.example.backend.securities.user;

import com.example.backend.controllers.controller_requests.ChangePasswordRequest;
import com.example.backend.controllers.controller_responses.AdminFindUsersResponse;
import com.example.backend.enums.UserStatusEnum;
import com.example.backend.generics.Pagination;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public AdminFindUsersResponse getAllUsers(String partUserAccount, String partUserEmail, int pageNo, int pageSize) {
        partUserAccount = "%" + partUserAccount + "%";
        partUserEmail = "%" + partUserEmail + "%";
        List<User> allUsers = userRepository.findUsersByPartUserAccountAndPartUserEmail(partUserAccount, partUserEmail);
        int totalPages = Math.floorDiv(allUsers.size(), pageSize) + 1 ;
        //Phân trang từ phần tử startElement đến endElement
        Pagination<User> pagination = new Pagination<User>();
        List<User> users = pagination.pagination(allUsers, pageNo, pageSize);

        //
        AdminFindUsersResponse adminFindUsersResponse = new AdminFindUsersResponse(users, pageNo, pageSize, totalPages, allUsers.size());
        return adminFindUsersResponse;
    }

    public User getSingleUser(Long userID) {
        return userRepository.findByUserID(userID).orElseThrow();
    }

    public String changePassword(Long userID, ChangePasswordRequest changePasswordRequest) {
        try {
            User user = userRepository.findByUserID(userID).orElseThrow();
            boolean comparePassword = passwordEncoder.matches(changePasswordRequest.getOldUserPassword(), user.getUserPassword());
            if (comparePassword) {
                user.setUserPassword(passwordEncoder.encode(changePasswordRequest.getNewUserPassword()));
                user.setUserUpdatedAt(LocalDateTime.now());
                userRepository.save(user);
                return "Change successfully password";
            }
            return "Wrong old password!";
        }catch(NoSuchElementException e){
            return "User with userID = " + userID + " does not exists";
        }
    }

    public String changePassword(Long userID, String newUserPassword) {
        try{
            User user = userRepository.findByUserID(userID).orElseThrow();
            if(user.getRole().getRoleID() == 1) return "Admin account can not change its password like this!";
            user.setUserPassword(passwordEncoder.encode(newUserPassword));
            user.setUserUpdatedAt(LocalDateTime.now());
            userRepository.save(user);
            return "Change password successfully!";
        }catch (NoSuchElementException e){
            return "User with userID = " + userID + " does not exists!";
        }
    }

    public String updateEmail(Long userID, String newUserEmail) {
        try {
            User user = userRepository.findByUserID(userID).orElseThrow();
            user.setUserEmail(newUserEmail);
            user.setUserUpdatedAt(LocalDateTime.now());
            userRepository.save(user);
            return "Update Successfully!";
        }catch (NoSuchElementException e) {
            return "User with userID = " + userID + " does not exists";
        }
    }

    public String deleteAccount(Long userID) {
        try {
            User user = userRepository.findByUserID(userID).orElseThrow();
            if (user.getRole().getRoleID() == 1) return "Can not delete admin account!";
            user.setUserStatus(UserStatusEnum.UNACTIVE);
            userRepository.save(user);
            return "Delete successfully!";
        }catch (NoSuchElementException e){
            return "User with userID = " + userID + " does not exists";
        }
    }

    public String activateAccount(Long userID) {
        try{
            User user = userRepository.findByUserID(userID).orElseThrow();
            if(user.getUserStatus().equals(UserStatusEnum.ACTIVE)) return "User already be active!";
            user.setUserStatus(UserStatusEnum.ACTIVE);
            user.setUserUpdatedAt(LocalDateTime.now());
            userRepository.save(user);
            return "activated successfully!";
        } catch (NoSuchElementException e){
            return "User does not exist";
        }
    }


}
