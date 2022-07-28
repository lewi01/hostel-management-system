package com.lewisCode.hostelbookingsystem.controller;

import com.lewisCode.hostelbookingsystem.entity.Hostel;
import com.lewisCode.hostelbookingsystem.entity.Room;
import com.lewisCode.hostelbookingsystem.service.BookingService;
import com.lewisCode.hostelbookingsystem.service.HostelService;
import com.lewisCode.hostelbookingsystem.service.RoomService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/admin")
@Validated
public class AdminController {

    private HostelService hostelService;
    private RoomService roomService;

    private BookingService bookingService;

    @PostMapping("/create/{phoneNumber}")
    public ResponseEntity<?> createHostel(@PathVariable String phoneNumber,
                                          @Valid @RequestBody Hostel hostel){
        hostelService.createHostel(phoneNumber, hostel);
        return ResponseEntity.ok(hostel.getName() +" was add successfully");
    }
    @PostMapping("/hostel/{hostelId}/create")
    public ResponseEntity<?> createRoom(@PathVariable Long hostelId,
                                        @Valid @RequestBody Room room){
        roomService.createRoom(hostelId, room);
        return ResponseEntity.ok(room.getName() +" was add successfully");
    }

    @PutMapping("/updateHotel/{name}")
    public ResponseEntity<?> updateHostel(@Valid @RequestBody Hostel hostel,
                                          @PathVariable String name){
        hostelService.updateHostel(hostel, name);
        return ResponseEntity.ok(hostel.getName() +" was update successfully");
    }
    @PutMapping("/updateRoom/{name}")
    public ResponseEntity<?> updateRoom(@Valid @RequestBody Room room,
                                        @PathVariable String name){
        roomService.updateRoom(room, name);
        return ResponseEntity.ok(room.getName() +" was update successfully");
    }
    @GetMapping("/admin/{phoneNumber}/get")
    public List<Hostel> getAllHostelAsPerAdminPhoneNumber(
            @PathVariable String phoneNumber ){
        return hostelService.findAllHostelByUserPhoneNumber(phoneNumber);
    }

    @DeleteMapping("/deleteHostel/{name}")
    public ResponseEntity<?> deleteHostel(@PathVariable String name){
        hostelService.deleteHostel(name);
        return ResponseEntity.ok(name +" was successfully deleted");
    }

    @DeleteMapping("/deleteRoom/{name}")
    public ResponseEntity<?> deleteRoom(@PathVariable String name){
        roomService.deleteRoom(name);
        return ResponseEntity.ok(name +" was successfully deleted");
    }
}
