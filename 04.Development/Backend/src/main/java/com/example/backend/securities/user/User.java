package com.example.backend.securities.user;

import com.example.backend.enums.UserStatusEnum;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "users", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"user_account"}),
        @UniqueConstraint(columnNames = {"user_email"})
})
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long userID;
    @Column(name = "user_account", nullable = false)
    private String userAccount;
    @Column(name = "user_password", nullable = false)
    private String userPassword;
    @Email
    @Column(name = "user_email", nullable = false)
    private String userEmail;
    @Column(name = "user_created_at", unique = true)
    private LocalDateTime userCreatedAt;
    @Column(name = "user_updated_at")
    private LocalDateTime userUpdatedAt;
    @Column(name = "user_status")
    @Enumerated(EnumType.STRING)
    private UserStatusEnum userStatus;
    @ManyToOne
    @JoinColumn(name = "role_id", referencedColumnName = "role_id")
    private Role role;

    public User(String userAccount, String userPassword, String userEmail, LocalDateTime userCreatedAt, LocalDateTime userUpdatedAt, Role role) {
        this.userAccount = userAccount;
        this.userPassword = userPassword;
        this.userEmail = userEmail;
        this.userCreatedAt = userCreatedAt;
        this.userUpdatedAt = userUpdatedAt;
        this.userStatus = UserStatusEnum.ACTIVE;
        this.role = role;
    }


    // account, password, email, createdAt, updatedAt, role_id
}
