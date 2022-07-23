package com.lewisCode.hostelbookingsystem.entity;

import com.lewisCode.hostelbookingsystem.role.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import java.util.HashSet;
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
    @Pattern(regexp = "^[0-9]{10}$", message = "Phone number must be 10 digits")
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
    private Set<Role> role = new HashSet<>();
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL,
            fetch = FetchType.LAZY)
    private List<Hostel> hostel;
    @OneToOne(mappedBy = "user")
    private Booking booking;
}
