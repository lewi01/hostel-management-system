package com.lewisCode.hostelbookingsystem.exeptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "USER NOT FOUND")
public class UserNotFound extends RuntimeException{
    public UserNotFound(String message) {
        super(message);
    }
}
