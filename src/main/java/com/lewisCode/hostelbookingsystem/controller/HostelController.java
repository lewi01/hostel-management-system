package com.lewisCode.hostelbookingsystem.controller;

import com.lewisCode.hostelbookingsystem.dto.HostelResponse;
import com.lewisCode.hostelbookingsystem.entity.Hostel;
import com.lewisCode.hostelbookingsystem.service.HostelService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
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

    @PostMapping("/admin/{adminId}/create")
    public ResponseEntity<?> createHostel(@PathVariable Long adminId,
                                          @Valid @RequestBody Hostel hostel){
        hostelService.createHostel(adminId,hostel);
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
        return hostelService.findAllHostelByAdminPhoneNumber(phoneNumber);
    }

}
