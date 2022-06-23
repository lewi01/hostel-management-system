package com.lewisCode.hostelbookingsystem.service;

import com.lewisCode.hostelbookingsystem.entity.Booking;
import com.lewisCode.hostelbookingsystem.entity.Room;
import com.lewisCode.hostelbookingsystem.exeptions.UserNotFound;
import com.lewisCode.hostelbookingsystem.repository.BookingRepository;
import com.lewisCode.hostelbookingsystem.repository.RoomRepository;
import com.lewisCode.hostelbookingsystem.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class BookingService {

    private BookingRepository bookingRepository;
    private RoomRepository roomRepository;
    private UserRepository userRepository;

    public void createBooking(String roomName,String userPhoneNumber,Booking booking){
        userRepository.findByPhoneNumber(userPhoneNumber) .map(user -> {
            booking.setUser(user);
            for (Room room : roomRepository.findAll()) {
                if (room.getName().equals(roomName)) {
                    if(bookingRepository.count() > room.getMaxStudentOccupants()) {
                        throw new UserNotFound("Room is full");
                    }
                    booking.setRoom(room);
                    break;
                }
            }
            booking.setStartDate(LocalDateTime.now());
            booking.setEndDate(booking.getEndDate());
            return bookingRepository.save(booking);
        }).orElseThrow(() -> new UserNotFound("User not found"));
    }

}
