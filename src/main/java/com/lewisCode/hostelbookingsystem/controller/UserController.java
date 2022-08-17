package com.lewisCode.hostelbookingsystem.controller;

import com.lewisCode.hostelbookingsystem.dto.UserResponse;
import com.lewisCode.hostelbookingsystem.entity.User;
import com.lewisCode.hostelbookingsystem.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/api/user")
@Validated
public class UserController {
    private UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<?> createUser(@RequestBody User user){
        userService.createUser(user);
        return ResponseEntity.ok(user.getName() +" was add successfully");
    }
    @PutMapping("/update/{mobileNo}")
    public ResponseEntity<?> updateUser(@RequestBody User user,
                                           @PathVariable String mobileNo){
        userService.updateUser(user, mobileNo);
        return ResponseEntity.ok(user.getName() +" was update successfully");
    }
    @DeleteMapping("/delete/{mobileNo}")
    public ResponseEntity<?> deleteUser(@PathVariable String mobileNo){
        userService.deleteUser(mobileNo);
        return ResponseEntity.ok(mobileNo +" was successfully deleted");
    }
    @GetMapping("/get/{mobileNo}")
    public UserResponse getUserDTO(@PathVariable String mobileNo){
           return userService.getUser(mobileNo);
    }

}
