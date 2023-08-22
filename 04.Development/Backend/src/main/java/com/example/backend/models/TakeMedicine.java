package com.example.backend.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "take_medicines")
@NoArgsConstructor
@AllArgsConstructor
public class TakeMedicine{
    @Id
    @Column(name = "rem_id")
    private Long remID;

    @OneToOne
    @MapsId
    @JoinColumn(name = "rem_id")
    private Reminder reminder;

    @ManyToOne
    @JoinColumn(name = "pre_id", referencedColumnName = "pre_id")
    private Prescription prescription;
}
