package com.lewisCode.hostelbookingsystem.admin;

import com.lewisCode.hostelbookingsystem.exeptions.UserExistException;
import com.lewisCode.hostelbookingsystem.exeptions.UserNotFound;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class AdminService {

    private AdminRepository adminRepository;

    public void createAdmin( Admin admin, String mobileNo){
        Optional<Admin> admin1 = adminRepository.findByMobileNumber(mobileNo);
        if (admin1.isPresent()){
            throw new UserExistException(admin.getMobileNumber()+ " Exist!");
        }
        adminRepository.save(admin);
    }
    public void updateAdmin(Admin a, String mobileNo)   {
        Optional<Admin> admin = adminRepository.findByMobileNumber(mobileNo);
        if (admin.isPresent()){
            admin.get().setFirstName(a.getFirstName());
            admin.get().setLastName(a.getLastName());
            admin.get().setMobileNumber(a.getMobileNumber());

            adminRepository.save(admin.get());
        }
        throw new UserNotFound(mobileNo +" not found");
    }
    public void deleteAdmin(String mobileNo) {
        Optional<Admin> admin = adminRepository.findByMobileNumber(mobileNo);
        if (admin.isEmpty()){
            throw new UserNotFound(mobileNo + " not found");
        }
        adminRepository.delete(admin.get());
    }
    public GetAdminDTO getAdminByMobileNumber(String mobileNo){
        Optional<Admin> admin = adminRepository.findByMobileNumber(mobileNo);
        GetAdminDTO getAdminDTO = new GetAdminDTO();
        if (admin.isPresent()){
            getAdminDTO.setFirstName(admin.get().getFirstName());
            getAdminDTO.setLastName(admin.get().getLastName());
            getAdminDTO.setMobileNumber(admin.get().getMobileNumber());

            return getAdminDTO;
        }
        throw new UserNotFound(mobileNo +" not found");
    }
}
