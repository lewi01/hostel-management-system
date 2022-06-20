package com.lewisCode.hostelbookingsystem.service;

import com.lewisCode.hostelbookingsystem.dto.HostelResponse;
import com.lewisCode.hostelbookingsystem.entity.Hostel;
import com.lewisCode.hostelbookingsystem.exeptions.UserNotFound;
import com.lewisCode.hostelbookingsystem.repository.HostelRepository;
import com.lewisCode.hostelbookingsystem.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class HostelService {

    private HostelRepository hostelRepository;
    private UserRepository userRepository;

    public void createHostel(Long adminId, Hostel hostel){
        userRepository.findById(adminId)
                .map(user -> {
                    hostel.setUser(user);
                    return hostelRepository.save(hostel);
                })
                .orElseThrow(() -> new UserNotFound("Not found admin with id " + adminId));

    }
    public  void  updateHostel(Hostel hostel, String name){
        Hostel hostel1 = hostelRepository.findByName(name);
        if (hostel1 == null){
            throw new UserNotFound("%s not found" .formatted(name));
        }
        hostel1.setName(hostel.getName());
        hostel1.setPhoneNumber(hostel.getPhoneNumber());
        hostelRepository.save(hostel1);
    }
    public HostelResponse findHostel(String name){
        Hostel hostel = hostelRepository.findByName(name);
        HostelResponse hostelResponse = new HostelResponse();
        if (hostel == null){
            throw new UserNotFound("%s not found" .formatted(name));
        }
        hostelResponse.setHostelName(hostel.getName());
        hostelResponse.setPhoneNumber(hostel.getPhoneNumber());
        return hostelResponse;
    }
    public List<Hostel> findAllHostelByUserPhoneNumber(String phoneNumber){
        if (!userRepository.existsByPhoneNumber(phoneNumber)){
            throw  new UserNotFound("%s not found".formatted(phoneNumber));
        }
        return hostelRepository.findByUserPhoneNumber(phoneNumber);
    }
    public void deleteHostel(String name){
        Hostel hostel = hostelRepository.findByName(name);
        if (hostel == null){
            throw new UserNotFound("%s not found" .formatted(name));
        }
        hostelRepository.delete(hostel);
    }
}
