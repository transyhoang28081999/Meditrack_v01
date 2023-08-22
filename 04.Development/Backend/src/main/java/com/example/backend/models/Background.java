package com.example.backend.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Table(name = "backgrounds")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Background {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "bg_id")
    private Long bgID;

    @Column(name = "bg_date", nullable = false, unique = true)
    private LocalDate bgDate;

    @ManyToOne
    @JoinColumn(name = "med_id", referencedColumnName = "med_id", nullable = false)
    private MedicalRecord medicalRecord;

    @Column(name = "bg_description")
    private String bgDescription;
}