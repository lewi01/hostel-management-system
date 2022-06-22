package com.lewisCode.hostelbookingsystem.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Integer maxStudentOccupants;
    private double cost;
    @OneToMany(mappedBy = "room",cascade = CascadeType.ALL,
            fetch = FetchType.LAZY)
    private List<Booking> booking;
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "hostel_id",nullable = false)
    private Hostel hostel;

}
