package com.lewisCode.hostelbookingsystem.controller;

import com.lewisCode.hostelbookingsystem.dto.HostelResponse;
import com.lewisCode.hostelbookingsystem.entity.Hostel;
import com.lewisCode.hostelbookingsystem.entity.MyUserDetails;
import com.lewisCode.hostelbookingsystem.service.HostelService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/hostel")
@Validated
public class HostelController {

    private HostelService hostelService;

    @PostMapping("/admin/create")
    public ResponseEntity<?> createHostel(@AuthenticationPrincipal MyUserDetails userDetails,
                                          @Valid @RequestBody Hostel hostel){
        hostelService.createHostel(userDetails.getUsername(),hostel);
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
    @GetMapping("/admin/{phoneNumber}/get")
    public List<Hostel> getAllHostelAsPerAdminPhoneNumber(
            @PathVariable String phoneNumber ){
        return hostelService.findAllHostelByUserPhoneNumber(phoneNumber);
    }
    @DeleteMapping("/delete/{name}")
    public ResponseEntity<?> deleteHostel(@PathVariable String name){
        hostelService.deleteHostel(name);
        return ResponseEntity.ok(name +" was successfully deleted");
    }
}
