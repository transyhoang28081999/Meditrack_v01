package com.example.backend.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "medical_records")
@NoArgsConstructor
@AllArgsConstructor
public class MedicalRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "med_id")
    private Long medID;
    @Column(name = "med_history", nullable = false)
    private String medHistory;
    @OneToOne
    @JoinColumn(name = "up_id", referencedColumnName = "up_id")
    private UserProfile userProfile;
}
