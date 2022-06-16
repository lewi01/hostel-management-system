package com.lewisCode.hostelbookingsystem.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Admin {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long Id;
   @Column
   private String firstName;
   @Column
   private String lastName;
   @Column
   private String phoneNumber;
   @OneToMany(mappedBy = "admin", cascade = CascadeType.ALL,
           fetch = FetchType.LAZY)
   private List<Hostel> hostel;
}
