package com.lewisCode.hostelbookingsystem.exeptions;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExceptionMessage {

    private LocalDateTime timestamp;
    private int status;
    private HttpStatus error;
    private String message;
    private String path;
}
