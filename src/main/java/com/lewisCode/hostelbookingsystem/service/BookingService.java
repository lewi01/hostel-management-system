package com.lewisCode.hostelbookingsystem.service;

import com.lewisCode.hostelbookingsystem.entity.Booking;
import com.lewisCode.hostelbookingsystem.entity.Room;
import com.lewisCode.hostelbookingsystem.entity.User;
import com.lewisCode.hostelbookingsystem.exeptions.UserNotFound;
import com.lewisCode.hostelbookingsystem.repository.BookingRepository;
import com.lewisCode.hostelbookingsystem.repository.RoomRepository;
import com.lewisCode.hostelbookingsystem.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class BookingService {

    private BookingRepository bookingRepository;
    private RoomRepository roomRepository;
    private UserRepository userRepository;

    public Booking createBooking(String roomName,String userPhoneNumber,Booking booking){
        Room room = roomRepository.findByName(roomName);
        Optional<User> user = userRepository.findByPhoneNumber(userPhoneNumber);
        if(user.isEmpty()){
            throw new UserNotFound("User not found");
        }
        if (room == null){
            throw new UserNotFound("Room not found");
        }
        if (user.get().getPhoneNumber().equals(userPhoneNumber)){
            throw new UserNotFound("You have already booked this room");
        }
        int bookingCount = bookingRepository.countByRoom(room);
        if (bookingCount > room.getMaxStudentOccupants()){
            throw new UserNotFound("Room is not available");
        }
        if (userRepository.existsByPhoneNumber(userPhoneNumber) && roomRepository.existsByName(roomName)){
            roomRepository.findByName(roomName).setRoomStatus(false);
            booking.setRoom(room);
            booking.setUser(user.get());
        }
        return bookingRepository.save(booking);
    }

}
