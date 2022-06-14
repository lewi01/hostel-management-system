//package com.lewisCode.hostelbookingsystem.entity;
//
//import lombok.AllArgsConstructor;
//import lombok.Data;
//import lombok.NoArgsConstructor;
//
//import javax.persistence.*;
//
//@Entity
//@NoArgsConstructor
//@AllArgsConstructor
//@Data
//public class Room {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//    private String name;
//    private int maxStudentOccupants;
//    private double cost;
//    @ManyToOne
//    @JoinColumn(name = "hostel_id",nullable = false)
//    private Hostel hostel;
//    @OneToOne
//    @JoinColumn(name = "booking_id", nullable = false)
//    private Booking booking;
//}
