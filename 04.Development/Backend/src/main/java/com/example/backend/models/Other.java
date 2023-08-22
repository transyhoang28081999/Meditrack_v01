package com.example.backend.models;

import com.example.backend.securities.user.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "others")
@NoArgsConstructor
@AllArgsConstructor
public class Other{
    @Id
    @Column(name = "rem_id")
    private Long remID;

    @OneToOne
    @MapsId
    @JoinColumn(name = "rem_id")
    private Reminder reminder;

    @ManyToOne
    @JoinColumn(name = "up_id", referencedColumnName = "up_id")
    private UserProfile userProfile;
}
