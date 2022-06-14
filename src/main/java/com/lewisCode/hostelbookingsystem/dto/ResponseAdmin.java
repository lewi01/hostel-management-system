package com.lewisCode.hostelbookingsystem.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseAdmin {

    private String firstName;
    private String lastName;
    private String phoneNumber;
}
