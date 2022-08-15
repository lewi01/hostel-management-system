package com.lewisCode.hostelbookingsystem.service;

import com.lewisCode.hostelbookingsystem.entity.User;
import com.lewisCode.hostelbookingsystem.exeptions.UserExistException;
import com.lewisCode.hostelbookingsystem.exeptions.UserNotFound;
import com.lewisCode.hostelbookingsystem.dto.UserResponse;
import com.lewisCode.hostelbookingsystem.repository.UserRepository;
import com.lewisCode.hostelbookingsystem.role.Role;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


import java.util.*;

@Service
@RequiredArgsConstructor
public class UserService{

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public void createUser(User user){
        Optional<User> user1 = userRepository.findByPhoneNumber(user.getPhoneNumber());
        if (user1.isPresent()){
            throw new UserExistException(user.getPhoneNumber()+ " Exist!");
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        //assignRole(user);
        if (userRepository.findAll().isEmpty()) {
            user.getRoles().clear();
            user.getRoles().add(Role.ADMIN);
        } else {
            user.getRoles().add(Role.STUDENT);
        }
        userRepository.save(user);
    }
    public void updateUser(User user, String mobileNo)   {
        Optional<User> user1 = userRepository.findByPhoneNumber(mobileNo);
        if (user1.isEmpty()){
            throw new UserNotFound(mobileNo +" not found");
        }
        user1.get().setName(user.getName());
        user1.get().setPhoneNumber(user.getPhoneNumber());
        user1.get().setEmail(user.getEmail());
        user1.get().setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user1.get());
    }
    public void deleteUser(String mobileNo) {
        Optional<User> user = userRepository.findByPhoneNumber(mobileNo);
        if (user.isEmpty()){
            throw new UserNotFound(mobileNo + " not found");
        }
        userRepository.delete(user.get());
    }
    public UserResponse getUser(String mobileNo){
        Optional<User> user = userRepository.findByPhoneNumber(mobileNo);
        UserResponse guestDTO = new UserResponse();
        if (user.isPresent()){
            guestDTO.setName(user.get().getName());
            guestDTO.setPhoneNumber(user.get().getPhoneNumber());
            return guestDTO;
        }
        throw new UserNotFound(mobileNo+" not found");
    }

}
