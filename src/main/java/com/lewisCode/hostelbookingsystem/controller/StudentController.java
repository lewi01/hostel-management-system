package com.lewisCode.hostelbookingsystem.controller;

import com.lewisCode.hostelbookingsystem.dto.StudentResponse;
import com.lewisCode.hostelbookingsystem.service.StudentService;
import com.lewisCode.hostelbookingsystem.entity.Student;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@AllArgsConstructor
@RequestMapping("/api/guest")
@Validated
public class StudentController {
    private StudentService studentService;

    @PostMapping("/create")
    public ResponseEntity<?> createGuest(@Valid @RequestBody Student student){
        studentService.createGuest(student, student.getPhoneNumber());
        return ResponseEntity.ok(student.getFirstName() +" was add successfully");
    }
    @PutMapping("/update/{mobileNo}")
    public ResponseEntity<?> updateGuest(@Valid @RequestBody Student student,
                                           @PathVariable String mobileNo){
        studentService.updateGuest(student, mobileNo);
        return ResponseEntity.ok(student.getFirstName() +" was update successfully");
    }
    @DeleteMapping("/delete/{mobileNo}")
    public ResponseEntity<?> deleteGuest(@PathVariable String mobileNo){
        studentService.deleteGuest(mobileNo);
        return ResponseEntity.ok(mobileNo +" was successfully deleted");
    }
    @GetMapping("/get/{mobileNo}")
    public StudentResponse getGuestDTO(@PathVariable String mobileNo){
           return studentService.getGuestByRegNumber(mobileNo);
    }

}
