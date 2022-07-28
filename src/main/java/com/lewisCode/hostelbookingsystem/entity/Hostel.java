package com.lewisCode.hostelbookingsystem.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Hostel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String phoneNumber;
    @Column
    private String name;
    @JsonIgnore
    @OneToMany(mappedBy = "hostel", cascade = CascadeType.ALL)
    @ToString.Exclude
    private List<Room> rooms;
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Hostel hostel = (Hostel) o;
        return id != null && Objects.equals(id, hostel.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
