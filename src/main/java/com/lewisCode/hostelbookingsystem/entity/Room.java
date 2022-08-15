package com.lewisCode.hostelbookingsystem.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private int occupant;
    private double cost;
    @OneToMany(mappedBy = "room",cascade = CascadeType.ALL,
            fetch = FetchType.LAZY)
    @ToString.Exclude
    private List<Booking> booking;
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "hostel_id",nullable = false)
    private Hostel hostel;

//    public boolean getNumberOfOccupant(long numberOfOccupant){
//        int number =0;
//       if (numberOfOccupant > getOccupant()){
//           System.out.println("Room is full");
//           return false;
//       }
//       number++;
//       return true;
//    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Room room = (Room) o;
        return id != null && Objects.equals(id, room.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
