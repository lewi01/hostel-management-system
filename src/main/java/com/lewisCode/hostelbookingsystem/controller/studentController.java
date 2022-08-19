package com.lewisCode.hostelbookingsystem.controller;

import com.lewisCode.hostelbookingsystem.dto.HostelResponse;
import com.lewisCode.hostelbookingsystem.entity.Booking;
import com.lewisCode.hostelbookingsystem.entity.Room;
import com.lewisCode.hostelbookingsystem.service.BookingService;
import com.lewisCode.hostelbookingsystem.service.HostelService;
import com.lewisCode.hostelbookingsystem.service.RoomService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/student")
@Validated
public class studentController {

    private RoomService roomService;

    private HostelService hostelService;

    private BookingService bookingService;

    @PreAuthorize("hasAuthority('STUDENT')")
    @PostMapping("/user/{userPhoneNumber}/book/{roomName}/create")
    public ResponseEntity<?> bookRoom(@PathVariable String roomName,
                                           @PathVariable String userPhoneNumber,
                                           @Valid @RequestBody Booking booking){
        bookingService.bookingRoom(roomName, userPhoneNumber, booking);
        return ResponseEntity.ok(booking.getUser().getName() +" has booked room " +
                booking.getRoom().getName() + " successfully");
    }
    @PreAuthorize("hasAuthority('STUDENT')")
    @GetMapping("/getHostel/{name}")
    public HostelResponse getHostel(@PathVariable String name){
        return hostelService.findHostel(name);
    }
    @GetMapping("/getRoom/{name}")
    public Room getRoom(@PathVariable String name){

        return roomService.findRoom(name);
    }
    @PreAuthorize("hasAuthority('STUDENT')")
    @GetMapping("/hostel/{name}")
    public List<Room> getAllRoom(
            @PathVariable String name ){
        return roomService.findAllRoomByHostelName(name);
    }

}
