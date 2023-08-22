package com.example.backend.models;

import com.example.backend.enums.ReminderEnum;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Data
@Entity
@Table(name = "reminders")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "rem_type", discriminatorType = DiscriminatorType.STRING)
@NoArgsConstructor
@AllArgsConstructor
public class Reminder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "rem_id")
    private Long remID;
    @Column(name = "rem_datetime")
    private LocalDateTime remDatetime;
    @Column(name = "rem_message", length = 500)
    private String remMessage;
    @Column(name = "rem_type", insertable = false, updatable = false)
    @Enumerated(EnumType.STRING)
    private ReminderEnum remType;
//    @OneToOne(mappedBy = "reminder", cascade = CascadeType.ALL)
//    private TakeMedicine takeMedicine;
//    @OneToOne(mappedBy = "reminder", cascade = CascadeType.ALL)
//    private RemindAppointment remindAppointment;
//    @OneToOne(mappedBy = "reminder", cascade = CascadeType.ALL)
//    private Other other;
    // type, datetime, message, app_id, pre_id
}
