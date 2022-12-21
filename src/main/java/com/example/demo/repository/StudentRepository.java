package com.example.demo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.model.Student;

public interface StudentRepository extends JpaRepository<Student, Long>{
    
    @Query("select s from Student s where s.email = ?1")
    Optional<Student> findStudentByEmail(String email);
}
