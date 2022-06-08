package com.lewisCode.hostelbookingsystem.admin;

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
        adminService.createAdmin(admin, admin.getMobileNumber());
        return ResponseEntity.ok(admin.getFirstName() +" was add successfully");
    }
    @PutMapping("/update/{mobileNo}")
    public ResponseEntity<?> updateAdmin(@Valid @RequestBody Admin admin,
                                           @PathVariable String mobileNo){
        adminService.updateAdmin(admin, mobileNo);
        return ResponseEntity.ok(admin.getFirstName() +" was update successfully");
    }
    @DeleteMapping("/delete/{mobileNo}")
    public ResponseEntity<?> deleteAdmin(@PathVariable String mobileNo){
        adminService.deleteAdmin(mobileNo);
        return ResponseEntity.ok(mobileNo +" was successfully deleted");
    }
    @GetMapping("/get/{mobileNo}")
    public GetAdminDTO getAdmin(@PathVariable String mobileNo){
        return adminService.getAdminByMobileNumber(mobileNo);
    }

}
