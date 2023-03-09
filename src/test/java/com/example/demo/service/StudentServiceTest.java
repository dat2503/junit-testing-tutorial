package com.example.demo.service;

import com.example.demo.model.Student;
import com.example.demo.repository.StudentRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class StudentServiceTest {

    @Mock
    private StudentRepository studentRepository;

    @InjectMocks
    StudentService studentService;
    private List<Student> studentList = new ArrayList<>();
    @BeforeEach
    public void setup(){
        MockitoAnnotations.openMocks(this);
        Student studentA = Student.builder()
                .name("student A")
                .age(19)
                .email("studentA@gmail.com")
                .build();
        Student studentB = Student.builder()
                .name("student B")
                .age(20)
                .email("studentB@gmail.com")
                .build();
        studentList.addAll(Arrays.asList(studentA, studentB));
    }

    @AfterEach
    public void tearDown(){
        studentList.clear();
    }
    @Test
    void getAllStudent() {
        when(studentRepository.findAll()).thenReturn(studentList);
        List<Student> actualList = studentService.getAllStudent();
        assertEquals(studentList, actualList);
        verify(studentRepository, times(1)).findAll();
    }

    @Test
    void addNewStudent() {
        Student studentC = Student.builder().name("studentC").age(22).email("studentC@gmail.com").build();
        when(studentRepository.save(any(Student.class))).thenReturn(studentC);
        studentService.addNewStudent(studentC);
    }

    @Test
    void deleteStudent() {

        when(studentRepository.existsById(1L)).thenReturn(true);
        studentService.deleteStudent(1L);
        verify(studentRepository, times(1)).existsById(1L);
        //verify(studentService, times(1)).deleteStudent(1L);
    }

    @Test
    void updateStudent() {
        when(studentRepository.findById(anyLong())).thenReturn(Optional.of(new Student()));
        when(studentRepository.findStudentByEmail(any())).thenReturn(Optional.empty());
        studentService.updateStudent(anyLong(), "johnny", "j@gmail.com");
    }
}