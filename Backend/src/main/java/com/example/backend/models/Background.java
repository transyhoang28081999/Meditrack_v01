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

    @EmbeddedId
    private BackgroundId id;

    @ManyToOne
    @MapsId("medID")
    @JoinColumn(name = "med_id")
    private MedicalRecord medicalRecord;

    @Column(name = "bg_description")
    private String bgDescription;

    @Data
    @Embeddable
    @NoArgsConstructor
    @AllArgsConstructor
    public static class BackgroundId implements Serializable {

        private Long medID;
        @Column(name = "bg_date")
        private LocalDate bgDate;
    }
}