package com.lewisCode.hostelbookingsystem.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String phoneNumber;
    private String firstName;
    private String lastName;
    @ManyToOne
    @JoinColumn(name = "hostel_id",nullable = false)
    private Hostel  hostel;
//    @ManyToOne
//    @JoinColumn(name = "room_id",nullable = false)
//    private Room room;
//    @ManyToOne
//    @JoinColumn(name = "booking_id",nullable = false)
//    private Booking booking;
}
