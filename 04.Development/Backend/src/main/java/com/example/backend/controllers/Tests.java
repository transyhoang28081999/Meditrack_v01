package com.example.backend.controllers;

import com.example.backend.models.Background;
import com.example.backend.repositories.BackgroundRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/v1.0/user/test")
public class Tests {
    @Autowired
    private BackgroundRepository backgroundRepository;
    @GetMapping
    public List<Background> test() {
        return backgroundRepository.findBackgroundByAll();
    }
}
