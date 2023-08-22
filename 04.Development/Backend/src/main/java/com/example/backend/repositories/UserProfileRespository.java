package com.example.backend.repositories;

import com.example.backend.enums.UserProfileEnum;
import com.example.backend.models.UserProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserProfileRespository extends JpaRepository<UserProfile, Long> {
    @Query("select up from UserProfile up where up.upID = :upID")
    Optional<UserProfile> findByUpID(Long upID);

    @Query("""
        select up from UserProfile up where up.user.userID = :userID
    """)
    List<UserProfile> findByUserID(Long userID);

    @Query("select up from UserProfile up where up.user.userID = :userID and up.upRole = :userProfileEnum")
    Optional<UserProfile> findSingleByUserID(Long userID, UserProfileEnum userProfileEnum);
}
