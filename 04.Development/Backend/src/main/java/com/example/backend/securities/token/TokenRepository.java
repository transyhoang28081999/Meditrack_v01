package com.example.backend.securities.token;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface TokenRepository extends JpaRepository<Token, Integer>{
    @Query(" select t from Token t inner join User u on t.user.userID = u.userID where u.userID = :userId and (t.expired = 0 or t.revoked = 0 ) ")
    List<Token> findAllValidTokensByUser(Long userId);

    Optional<Token> findByToken(String token);
}

