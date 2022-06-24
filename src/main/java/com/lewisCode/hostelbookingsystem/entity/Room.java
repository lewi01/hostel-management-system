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
    private int occupant;
    private double cost;
    @OneToMany(mappedBy = "room",cascade = CascadeType.ALL,
            fetch = FetchType.LAZY)
    private List<Booking> booking;
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "hostel_id",nullable = false)
    private Hostel hostel;


    public int getCapacity() {
        int capacity = 0;
        for (Booking booking : this.booking) {
            if (capacity< booking.getRoom().getOccupant()) {
                capacity++;
                break;
            }
        }
        return capacity;
    }
}
