package com.lewisCode.hostelbookingsystem.service;

import com.lewisCode.hostelbookingsystem.dto.ResponseAdmin;
import com.lewisCode.hostelbookingsystem.entity.Admin;
import com.lewisCode.hostelbookingsystem.exeptions.UserExistException;
import com.lewisCode.hostelbookingsystem.exeptions.UserNotFound;
import com.lewisCode.hostelbookingsystem.repository.AdminRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class AdminService {

    private AdminRepository adminRepository;

    public void createAdmin(Admin admin,String phoneNumber){
        Optional<Admin> admin1 = adminRepository.findByPhoneNumber(phoneNumber);
        if (admin1.isPresent()){
            throw new UserExistException(admin.getPhoneNumber() + " Exist!");
        }
        adminRepository.save(admin);
    }
    public void updateAdmin(Admin admin, String phoneNumber)   {
        Optional<Admin> admin1 = adminRepository.findByPhoneNumber(phoneNumber);
        if (admin1.isPresent()){
            admin1.get().setFirstName(admin.getFirstName());
            admin1.get().setLastName(admin.getLastName());
            admin1.get().setPhoneNumber(admin.getPhoneNumber());
            adminRepository.save(admin1.get());
        }
        throw new UserNotFound(phoneNumber +" not found");
    }
    public void deleteAdmin( String phoneNumber) {
        Optional<Admin> admin = adminRepository.findByPhoneNumber(phoneNumber);
        if (admin.isEmpty()){
            throw new UserNotFound("%s not found".formatted(phoneNumber));
        }
        adminRepository.delete(admin.get());
    }
    public ResponseAdmin findAdminByPhoneNumber(String phoneNumber){
        Optional<Admin> admin = adminRepository.findByPhoneNumber(phoneNumber);
        ResponseAdmin responseAdmin =  new ResponseAdmin();
        if (admin.isPresent()){
            responseAdmin.setFirstName(admin.get().getFirstName());
            responseAdmin.setLastName(admin.get().getLastName());
            responseAdmin.setPhoneNumber(admin.get().getPhoneNumber());
            return responseAdmin;
        }
        throw new UserNotFound("%s not found".formatted(admin.get().getFirstName()));
    }
}
