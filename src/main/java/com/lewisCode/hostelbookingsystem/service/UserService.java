package com.lewisCode.hostelbookingsystem.service;

import com.lewisCode.hostelbookingsystem.entity.MyUserDetails;
import com.lewisCode.hostelbookingsystem.entity.User;
import com.lewisCode.hostelbookingsystem.exeptions.UserExistException;
import com.lewisCode.hostelbookingsystem.exeptions.UserNotFound;
import com.lewisCode.hostelbookingsystem.dto.UserResponse;
import com.lewisCode.hostelbookingsystem.repository.UserRepository;
import com.lewisCode.hostelbookingsystem.role.Role;
import lombok.AllArgsConstructor;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

@Service
@AllArgsConstructor
public class UserService implements UserDetailsService {

    private UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private static boolean isFirst = true;

    @Override
    public UserDetails loadUserByUsername(String phoneNumber) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findByPhoneNumber(phoneNumber);
        if (user.isEmpty()) {
            throw new UsernameNotFoundException("User with " + phoneNumber +" not found");
        }
        return new MyUserDetails(user.get());
    }

    public void createUser(User user, String mobileNo){
        Optional<User> user1 = userRepository.findByPhoneNumber(mobileNo);
        if (user1.isPresent()){
            throw new UserExistException(user.getPhoneNumber()+ " Exist!");
        }
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setRole(Set.of(isFirst? Role.ADMIN:Role.STUDENT));
        isFirst = false;
        userRepository.save(user);
    }
    public void updateUser(User user, String mobileNo)   {
        Optional<User> user1 = userRepository.findByPhoneNumber(mobileNo);
        if (user1.isEmpty()){
            throw new UserNotFound(mobileNo +" not found");
        }
        user1.get().setFirstName(user.getFirstName());
        user1.get().setLastName(user.getLastName());
        user1.get().setPhoneNumber(user.getPhoneNumber());
        user1.get().setEmail(user.getEmail());
        user1.get().setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
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
        Optional<User> guest = userRepository.findByPhoneNumber(mobileNo);
        UserResponse guestDTO = new UserResponse();
        if (guest.isPresent()){
            guestDTO.setFirstName(guest.get().getFirstName());
            guestDTO.setLastName(guest.get().getLastName());
            guestDTO.setPhoneNumber(guest.get().getPhoneNumber());
            return guestDTO;
        }
        throw new UserNotFound(mobileNo+" not found");
    }

}
