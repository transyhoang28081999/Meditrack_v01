package com.example.backend.repositories;

import com.example.backend.models.UserProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserProfileRespository extends JpaRepository<UserProfile, Long> {
}
