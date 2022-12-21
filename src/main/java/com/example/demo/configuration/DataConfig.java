package com.example.demo.configuration;

import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.demo.model.Student;
import com.example.demo.repository.StudentRepository;

@Configuration
public class DataConfig {
    
    @Bean
    CommandLineRunner commandLineRunner(StudentRepository studentRepository){
        return args ->{
            Student maria = new Student(
                1L,
                "maria",
                "maria@gmail.com",
                21
            );
            Student alex = new Student(
                2L,
                "alex",
                "alex@gmail.com",
                20
            );

            studentRepository.saveAll(List.of(maria, alex));
        };
    }

}
