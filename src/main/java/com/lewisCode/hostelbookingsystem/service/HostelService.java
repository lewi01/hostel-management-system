package com.lewisCode.hostelbookingsystem.service;

import com.lewisCode.hostelbookingsystem.dto.HostelResponse;
import com.lewisCode.hostelbookingsystem.entity.Admin;
import com.lewisCode.hostelbookingsystem.entity.Hostel;
import com.lewisCode.hostelbookingsystem.exeptions.UserExistException;
import com.lewisCode.hostelbookingsystem.exeptions.UserNotFound;
import com.lewisCode.hostelbookingsystem.repository.AdminRepository;
import com.lewisCode.hostelbookingsystem.repository.HostelRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
@AllArgsConstructor
public class HostelService {

    private HostelRepository hostelRepository;
    private AdminRepository adminRepository;

    public void createHostel(Hostel hostel, String name){
        Hostel hostel1 = hostelRepository.findByName(name);
        if (hostel1 != null){
            throw new UserExistException("%s Exists!" .formatted(name));
        }
        hostelRepository.save(hostel);
    }
    public  void  updateHostel(Hostel hostel, String name){
        Hostel hostel1 = hostelRepository.findByName(name);
        if (hostel1 == null){
            throw new UserNotFound("%s not found" .formatted(name));
        }
        hostel1.setName(hostel.getName());
        hostelRepository.save(hostel1);
    }
    public HostelResponse findHostel(String name){
        Hostel hostel = hostelRepository.findByName(name);
        HostelResponse hostelResponse = new HostelResponse();
        if (hostel == null){
            throw new UserNotFound("%s not found" .formatted(name));
        }
        hostelResponse.setHostelName(hostel.getName());
        return hostelResponse;
    }
    public HostelResponse findHostelByAdmin(String phoneNumber){
        Optional<Admin> admin = adminRepository.findByPhoneNumber(phoneNumber);
        Hostel hostel = hostelRepository.findHostelByAdminPhoneNumber(phoneNumber);
        HostelResponse hostelResponse = new HostelResponse();
        if (hostel != null){
            hostelResponse.setHostelName(hostel.getName());
            return hostelResponse;
        }
        throw  new UserNotFound("%s not found" .formatted(admin.get().getHostel().getName()));
    }
}
