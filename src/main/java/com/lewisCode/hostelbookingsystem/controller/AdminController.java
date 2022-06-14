package com.lewisCode.hostelbookingsystem.controller;

import com.lewisCode.hostelbookingsystem.dto.ResponseAdmin;
import com.lewisCode.hostelbookingsystem.dto.StudentResponse;
import com.lewisCode.hostelbookingsystem.entity.Admin;
import com.lewisCode.hostelbookingsystem.entity.Student;
import com.lewisCode.hostelbookingsystem.service.AdminService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@AllArgsConstructor
@RequestMapping("/api/admin")
@Validated
public class AdminController {

    private AdminService adminService;

    @PostMapping("/create")
    public ResponseEntity<?> createAdmin(@Valid @RequestBody Admin admin){
        adminService.createAdmin(admin, admin.getPhoneNumber());
        return ResponseEntity.ok(admin.getFirstName() +" was add successfully");
    }
    @PutMapping("/update/{phoneNo}")
    public ResponseEntity<?> updateAdmin(@Valid @RequestBody Admin admin,
                                         @PathVariable String phoneNo){
        adminService.updateAdmin(admin, phoneNo);
        return ResponseEntity.ok(admin.getFirstName() +" was update successfully");
    }
    @DeleteMapping("/delete/{phoneNo}")
    public ResponseEntity<?> deleteGuest(@PathVariable String phoneNo){
        adminService.deleteAdmin(phoneNo);
        return ResponseEntity.ok(phoneNo +" was successfully deleted");
    }
    @GetMapping("/get/{phoneNo}")
    public ResponseAdmin getAdmin(@PathVariable String phoneNo){
        return adminService.findAdminByPhoneNumber(phoneNo);
    }

}
