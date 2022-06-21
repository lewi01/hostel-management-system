package com.lewisCode.hostelbookingsystem.repository;

import com.lewisCode.hostelbookingsystem.entity.Booking;
import com.lewisCode.hostelbookingsystem.entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookingRepository extends JpaRepository<Booking,Long> {
        Booking findByRoomName(String name);
        Booking findByUserPhoneNumber(String phoneNumber);

        int countByRoom(Room room);
}
