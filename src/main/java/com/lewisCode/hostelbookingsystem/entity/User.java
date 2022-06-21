package com.lewisCode.hostelbookingsystem.entity;

import com.lewisCode.hostelbookingsystem.role.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String phoneNumber;
    @Column
    private String firstName;
    @Column
    private String lastName;
    @Column
    private String email;
    @Column
    private String password;
    @ElementCollection
    @Enumerated(EnumType.STRING)
    private Set<Role> role;
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL,
            fetch = FetchType.LAZY)
    private List<Hostel> hostel;
    @OneToOne(mappedBy = "user")
    private Booking booking;
}
