package com.lewisCode.hostelbookingsystem.student;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetStudentDTO {

    private String phoneNumber;
    private String regNumber;
    private String firstName;
    private String lastName;
}
