package com.taintedmonk.h2learning.h2demoapp.service;

import com.taintedmonk.h2learning.h2demoapp.entity.Student;
import com.taintedmonk.h2learning.h2demoapp.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentService {
    @Autowired
    private StudentRepository studentRepository;

    public Student register(Student student) {
        return studentRepository.save(student);
    }

    public Student getDetails(String username) {
        return studentRepository.findBySname(username);
    }

    public String getStudentRoles(String username) {
        return studentRepository.findBySname(username).getSrole();
    }
}
