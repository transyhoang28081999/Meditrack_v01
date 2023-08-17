package com.example.backend.models;

import com.example.backend.securities.user.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
@Data
@Entity
@Table(name = "user_profiles")
@NoArgsConstructor
@AllArgsConstructor
public class UserProfile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "up_id")
    private Long upID;
    @Column(nullable = false, name = "up_name")
    private String upName;
    @Column(name = "up_dob") // Date of Birth
    private LocalDate upDoB;
    @Column(name = "up_phone")
    private String upPhone;
    @Column(name = "up_gender")
    private String upGender;
    @Column(name = "up_idcode")
    private String upIDCode;
    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "user_id", unique = true)
    private User user;
    @OneToOne(mappedBy = "userProfile")
    private MedicalRecord medicalRecord;
}
