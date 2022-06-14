package com.lewisCode.hostelbookingsystem.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Admin {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long Id;
   private String firstName;
   private String lastName;
   private String phoneNumber;
   @OneToOne
   @JoinColumn(name = "admin_id")
   private Hostel hostel;
}
