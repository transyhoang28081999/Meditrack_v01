package com.example.backend.models;

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
    @Column(name = "pre_medicine")
    private String preMedicine;
    @Column(name = "pre_dosage")
    private Integer preDosage;
    @Column(name = "pre_duration")
    private String preDuration;
    @Column(name = "pre_notes")
    private String preNotes;
    @Column(name = "pre_doctor")
    private String preDoctor;
    // medicine, dosage, duration, notes, doctor
}
