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
    public Student updateStudent(Student student, String regNo)   {
        Optional<Student> student1 = studentRepository.findByRegNumber(regNo);
        if (student1.isPresent()){
            student1.get().setFirstName(student.getFirstName());
            student1.get().setLastName(student.getLastName());
            student1.get().setPhoneNumber(student.getPhoneNumber());
            student1.get().setRegNumber(student.getRegNumber());
            return studentRepository.save(student1.get());
        }
        throw new UserNotFound(regNo+" not found");
    }
    public void deleteStudent(String regNo)   {
        Optional<Student> student1 = studentRepository.findByRegNumber(regNo);
        student1.ifPresent(student -> studentRepository.delete(student));
        throw new UserNotFound(regNo+" not found");
    }
    public Student getStudentByRegNumber(String regNo){
        Optional<Student> student1 = studentRepository.findByRegNumber(regNo);
        if (student1.isPresent()){
            return student1.get();
        }
        throw new UserNotFound(regNo+" not found");
    }
}
