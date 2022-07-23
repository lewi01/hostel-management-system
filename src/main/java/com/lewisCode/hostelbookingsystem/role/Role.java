package com.lewisCode.hostelbookingsystem.role;

import com.google.common.collect.Sets;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Set;

@AllArgsConstructor
@Getter
public enum Role {
    ADMIN,
    STUDENT
//    private final Set<Authorities> authorities;

}
