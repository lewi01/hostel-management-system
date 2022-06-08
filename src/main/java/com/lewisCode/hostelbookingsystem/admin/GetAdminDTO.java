package com.lewisCode.hostelbookingsystem.admin;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class GetAdminDTO {
    private String firstName;
    private  String lastName;
    private String mobileNumber;
}
