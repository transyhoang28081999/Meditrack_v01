package com.example.backend.securities.token;

import com.example.backend.securities.user.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tokens")
public class Token {
    @Id
    @GeneratedValue
    private int id;


    private String token;
    @Enumerated(EnumType.STRING)
    private TokenType tokenType;

    private Integer expired;
    private Integer revoked;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    private User user;

    public boolean isExpired() {
        return this.expired == 1;
    }
    public boolean isRevoked(){
        return this.revoked == 1;
    }
}
