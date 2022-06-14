package com.lewisCode.hostelbookingsystem.service;

import com.lewisCode.hostelbookingsystem.exeptions.UserExistException;
import com.lewisCode.hostelbookingsystem.exeptions.UserNotFound;
import com.lewisCode.hostelbookingsystem.dto.StudentResponse;
import com.lewisCode.hostelbookingsystem.entity.Student;
import com.lewisCode.hostelbookingsystem.repository.StudentRepository;
import lombok.AllArgsConstructor;

import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class StudentService{

    private StudentRepository studentRepository;


    public void createGuest(Student student, String mobileNo){
        Optional<Student> guest1 = studentRepository.findByPhoneNumber(mobileNo);
        if (guest1.isPresent()){
            throw new UserExistException(student.getPhoneNumber()+ " Exist!");
        }
        studentRepository.save(student);
    }
    public void updateGuest(Student student, String mobileNo)   {
        Optional<Student> guest1 = studentRepository.findByPhoneNumber(mobileNo);
        if (guest1.isPresent()){
            guest1.get().setFirstName(student.getFirstName());
            guest1.get().setLastName(student.getLastName());
            guest1.get().setPhoneNumber(student.getPhoneNumber());
            studentRepository.save(guest1.get());
            return;
        }
        throw new UserNotFound(mobileNo+" not found");
    }
    public void deleteGuest(String mobileNo) {
        Optional<Student> guest = studentRepository.findByPhoneNumber(mobileNo);
        if (guest.isEmpty()){
            throw new UserNotFound(mobileNo + " not found");
        }
        studentRepository.delete(guest.get());
    }
    public StudentResponse getGuestByRegNumber(String mobileNo){
        Optional<Student> guest = studentRepository.findByPhoneNumber(mobileNo);
        StudentResponse guestDTO = new StudentResponse();
        if (guest.isPresent()){
            guestDTO.setFirstName(guest.get().getFirstName());
            guestDTO.setLastName(guest.get().getLastName());
            guestDTO.setPhoneNumber(guest.get().getPhoneNumber());
            return guestDTO;
        }
        throw new UserNotFound(mobileNo+" not found");
    }


}
