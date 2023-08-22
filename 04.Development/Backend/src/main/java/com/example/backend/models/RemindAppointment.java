package com.example.backend.models;

import com.example.backend.enums.ReminderEnum;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "remind_appointments")
@NoArgsConstructor
@AllArgsConstructor
public class RemindAppointment{
    @Id
    @Column(name = "rem_id")
    private Long remID;

    @OneToOne
    @MapsId
    @JoinColumn(name = "rem_id")
    private Reminder reminder;

    @ManyToOne
    @JoinColumn(name = "app_id", referencedColumnName = "app_id")
    private Appointment appointment;
}
