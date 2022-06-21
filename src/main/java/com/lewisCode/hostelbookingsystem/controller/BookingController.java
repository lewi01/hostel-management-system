package com.lewisCode.hostelbookingsystem.controller;

import com.lewisCode.hostelbookingsystem.entity.Booking;
import com.lewisCode.hostelbookingsystem.service.BookingService;
import lombok.AllArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@AllArgsConstructor
@RequestMapping("/api/booking")
@Validated
public class BookingController {

    private BookingService bookingService;
    @PostMapping("/create")
    public Booking createBooking(@PathVariable String roomName, @PathVariable String userPhoneNumber,
                                 @Valid @RequestBody Booking booking){
        return bookingService.createBooking(roomName, userPhoneNumber, booking);
    }
}
