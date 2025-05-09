package com.sampleapi.demo;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public List<Student> getStudents() {
        return studentRepository.findAll();
    }
    
    public void addNewStudent(Student student) {
        Optional<Student> studentByEmail = studentRepository.findStudentByEmail(student.getEmail());

        if (studentByEmail.isPresent()) {
            throw new IllegalStateException("Email taken");
        }

        studentRepository.save(student);
        System.out.println(student); 
    }

    public void deleteStudent(Long studentId){
        boolean exists = studentRepository.existsById(studentId);
        if (!exists) {
            throw new IllegalStateException("Student with id " + studentId + " does not exist");
        }
        studentRepository.deleteById(studentId);
    }

    @Transactional
    public void updateStudent(Long studentId, String name, String email) {
        Optional<Student> student = studentRepository.findById(studentId);
        if (!student.isPresent()) {
            throw new IllegalStateException("Student with id " + studentId + " does not exist");
        }

        if (name != null && name.length() > 0 && !name.equals(student.get().getName())) {
            student.get().setName(name);
        }   

        if (email != null && email.length() > 0 && !email.equals(student.get().getEmail())) {
            Optional<Student> studentByEmail = studentRepository.findStudentByEmail(email);
            if (studentByEmail.isPresent()) {
                throw new IllegalStateException("Email taken");
            }
            student.get().setEmail(email);
        }
    }

}
