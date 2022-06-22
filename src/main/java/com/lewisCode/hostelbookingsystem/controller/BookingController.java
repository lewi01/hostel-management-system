package com.lewisCode.hostelbookingsystem.controller;

import com.lewisCode.hostelbookingsystem.entity.Booking;
import com.lewisCode.hostelbookingsystem.service.BookingService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@AllArgsConstructor
@RequestMapping("/api/booking")
@Validated
public class BookingController {

    private BookingService bookingService;
    @PostMapping("/user/{userPhoneNumber}/book/{roomName}/create")
    public ResponseEntity<?> createBooking(@PathVariable String roomName,
                                           @PathVariable String userPhoneNumber,
                                        @Valid @RequestBody Booking booking){
         bookingService.createBooking(roomName, userPhoneNumber, booking);
         return ResponseEntity.ok(booking.getUser().getLastName() +" has booked " +
                 booking.getRoom().getName() +"  successfully");
    }
    @GetMapping("/get/")
    public long getAllBookings(){
        return bookingService.getAllBookings();
    }
}
