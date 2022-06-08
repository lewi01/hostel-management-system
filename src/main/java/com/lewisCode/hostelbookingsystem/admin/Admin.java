package com.lewisCode.hostelbookingsystem.admin;

import com.lewisCode.hostelbookingsystem.hostel.Hostel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Admin {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private  String lastName;
    private String mobileNumber;

    @OneToOne
    @JoinColumn(name = "hostel_id", nullable = false)
    private Hostel hostel;
}
