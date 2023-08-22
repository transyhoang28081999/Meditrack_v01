package com.example.backend.models;

import com.example.backend.securities.user.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "prescriptions")
@NoArgsConstructor
@AllArgsConstructor
public class Prescription {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pre_id")
    private Long preID;
    @Column(name = "pre_medicine", nullable = false)
    private String preMedicine;
    @Column(name = "pre_dosage", nullable = false)
    private Integer preDosage;
    @Column(name = "pre_duration", nullable = false)
    private String preDuration;
    @Column(name = "pre_notes", length = 1000)
    private String preNotes;
    @Column(name = "pre_doctor")
    private String preDoctor;
    @ManyToOne
    @JoinColumn(name = "up_id", referencedColumnName = "up_id")
    private UserProfile userProfile;
    // medicine, dosage, duration, notes, doctor
}
