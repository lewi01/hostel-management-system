package com.lewisCode.hostelbookingsystem.student;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@AllArgsConstructor
@RequestMapping("/api/student")
@Validated
public class StudentController {
    private StudentService studentService;
    @PostMapping("/create")
    public ResponseEntity<?> createStudent(@Valid @RequestBody Student student){
        studentService.createStudent(student,student.getRegNumber());
        return ResponseEntity.ok(student.getFirstName() +" was add successfully");
    }
    @PutMapping("/update/{regNo}")
    public ResponseEntity<?> updateStudent(@Valid @RequestBody Student student,
                                           @PathVariable String regNo){
        studentService.updateStudent(student, regNo);
        return ResponseEntity.ok(student.getFirstName() +" was update successfully");
    }
    @DeleteMapping("/delete/{regNo}")
    public ResponseEntity<?> deleteStudent(@PathVariable String regNo){
        studentService.deleteStudent(regNo);
        return ResponseEntity.ok(regNo +" was successfully deleted");
    }
    @GetMapping("/get/{regNo}")
    public  GetStudentDTO getStudent(@PathVariable String regNo){
           return studentService.getStudentByRegNumber(regNo);
    }

}
