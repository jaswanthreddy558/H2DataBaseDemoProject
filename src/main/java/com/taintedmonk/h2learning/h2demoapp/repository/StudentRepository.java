package com.taintedmonk.h2learning.h2demoapp.repository;

import com.taintedmonk.h2learning.h2demoapp.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<Student,Long> {
    public Student findBySname(String username);

}
