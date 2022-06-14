package com.lewisCode.hostelbookingsystem.entity;

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
    @Column
    private String name;
    @OneToMany(mappedBy = "hostel", cascade = CascadeType.ALL)
    private List<Student> student;
    @OneToMany(mappedBy = "hostel", cascade = CascadeType.ALL)
    private List<Admin> admin;

}
