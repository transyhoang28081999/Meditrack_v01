package com.example.backend.securities.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUserEmail(String email);

    Optional<User> findByUserAccountOrUserEmail(String userAccount, String userEmail);

    Optional<User> findByUserID(Long userID);

    @Query("""
        select u from User u 
        where u.userAccount like :partUserAccount and u.userEmail like :partUserEmail 
        order by u.userAccount
    """)
    List<User> findUsersByPartUserAccountAndPartUserEmail(String partUserAccount, String partUserEmail);
}
