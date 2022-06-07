package com.lewisCode.hostelbookingsystem.student;

import com.lewisCode.hostelbookingsystem.exeptions.UserExistException;
import com.lewisCode.hostelbookingsystem.exeptions.UserNotFound;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class StudentService {

    private StudentRepository studentRepository;

    public void createStudent(Student student, String regNo){
        Optional<Student> student1 = studentRepository.findByRegNumber(regNo);
        if (student1.isPresent()){
            throw new UserExistException(student.getPhoneNumber()+ " Exist!");
        }
        studentRepository.save(student);
    }
    public void updateStudent(Student student, String regNo)   {
        Optional<Student> student1 = studentRepository.findByRegNumber(regNo);
        if (student1.isPresent()){
            student1.get().setFirstName(student.getFirstName());
            student1.get().setLastName(student.getLastName());
            student1.get().setPhoneNumber(student.getPhoneNumber());
            student1.get().setRegNumber(student.getRegNumber());
            studentRepository.save(student1.get());
            return;
        }
        throw new UserNotFound(regNo+" not found");
    }
    public void deleteStudent(String regNo)   {
        Optional<Student> student1 = studentRepository.findByRegNumber(regNo);
        student1.ifPresent(student -> studentRepository.delete(student));
        throw new UserNotFound(regNo+" not found");
    }
    public GetStudentDTO getStudentByRegNumber(String regNo){
        Optional<Student> student1 = studentRepository.findByRegNumber(regNo);
        GetStudentDTO student = new GetStudentDTO();
        if (student1.isPresent()){
            student.setFirstName(student1.get().getFirstName());
            student.setLastName(student1.get().getLastName());
            student.setRegNumber(student1.get().getRegNumber());
            student.setPhoneNumber(student1.get().getPhoneNumber());
            return student;
        }
        throw new UserNotFound(regNo+" not found");
    }
}
