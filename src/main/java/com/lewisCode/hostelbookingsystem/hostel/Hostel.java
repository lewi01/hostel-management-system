package com.lewisCode.hostelbookingsystem.hostel;

import com.lewisCode.hostelbookingsystem.admin.Admin;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Hostel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String HostelName;

    @OneToMany()
    @JoinColumn(name = "admin_id",nullable = false)
    private List<Admin> admin;
}
