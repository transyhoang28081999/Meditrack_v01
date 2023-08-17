package com.example.backend.controllers.controller_responses;

import com.example.backend.securities.user.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AdminFindUsersResponse {
    private List<User> users;
    private int currentPage;
    private int pageSize;
    private int totalPages;
    private int totalUsers;
}
