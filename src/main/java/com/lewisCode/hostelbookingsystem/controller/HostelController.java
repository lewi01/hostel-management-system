package com.lewisCode.hostelbookingsystem.controller;

import com.lewisCode.hostelbookingsystem.dto.HostelResponse;
import com.lewisCode.hostelbookingsystem.entity.Hostel;
import com.lewisCode.hostelbookingsystem.service.HostelService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@AllArgsConstructor
@RequestMapping("/api/hostel")
@Validated
public class HostelController {

    private HostelService hostelService;

    @PostMapping("/create")
    public ResponseEntity<?> createHostel(@Valid @RequestBody Hostel hostel){
        hostelService.createHostel(hostel, hostel.getName());
        return ResponseEntity.ok(hostel.getName() +" was add successfully");
    }
    @PutMapping("/update/{name}")
    public ResponseEntity<?> updateHostel(@Valid @RequestBody Hostel hostel,
                                         @PathVariable String name){
        hostelService.updateHostel(hostel, name);
        return ResponseEntity.ok(hostel.getName() +" was update successfully");
    }
    @GetMapping("/get/{name}")
    public HostelResponse getHostel(@PathVariable String name){
        return hostelService.findHostel(name);
    }
    @GetMapping("/get/number/{phoneNo}")
    public HostelResponse getByAdminPhoneNumber(@PathVariable String phoneNo){
        return hostelService.findHostelByAdmin(phoneNo);
    }

}
