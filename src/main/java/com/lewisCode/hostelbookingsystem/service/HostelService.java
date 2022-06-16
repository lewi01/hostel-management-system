package com.lewisCode.hostelbookingsystem.service;

import com.lewisCode.hostelbookingsystem.dto.HostelResponse;
import com.lewisCode.hostelbookingsystem.entity.Hostel;
import com.lewisCode.hostelbookingsystem.exeptions.UserNotFound;
import com.lewisCode.hostelbookingsystem.repository.AdminRepository;
import com.lewisCode.hostelbookingsystem.repository.HostelRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class HostelService {

    private HostelRepository hostelRepository;
    private AdminRepository adminRepository;

    public void createHostel(Long adminId, Hostel hostel){
        adminRepository.findById(adminId)
                .map(admin -> {
                    hostel.setAdmin(admin);
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
    public List<Hostel> findAllHostelByAdminPhoneNumber( String phoneNumber){
        if (!adminRepository.existsByPhoneNumber(phoneNumber)){
            throw  new UserNotFound("%s not found".formatted(phoneNumber));
        }
        return hostelRepository.findByAdminPhoneNumber(phoneNumber);
    }
}
