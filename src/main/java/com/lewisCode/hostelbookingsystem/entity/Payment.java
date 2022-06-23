package com.lewisCode.hostelbookingsystem.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private boolean paid;
    private double amount;
    private double balance;
    private String phoneNumber;
    private LocalDateTime date;
    @ManyToOne
    @JoinColumn(name = "booking_id")
    private Booking booking;

}
