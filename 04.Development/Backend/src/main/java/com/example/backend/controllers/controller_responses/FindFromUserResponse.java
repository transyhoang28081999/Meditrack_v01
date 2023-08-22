package com.example.backend.controllers.controller_responses;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FindFromUserResponse<T> {
    private List<T> data;
    private int currentPage;
    private int pageSize;
    private int totalPages;
    private int totalElements;
}
