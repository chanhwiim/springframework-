package com.nhnacademy.edu.springframework.project.service;

import com.nhnacademy.edu.springframework.project.config.ServiceConfig;
import com.nhnacademy.edu.springframework.project.repository.CsvStudents;
import com.nhnacademy.edu.springframework.project.repository.Score;
import com.nhnacademy.edu.springframework.project.repository.Students;
import com.nhnacademy.edu.springframework.project.service.DefaultStudentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@SpringBootTest(classes = ServiceConfig.class)
class StudentServiceTest {

    @Mock
    private Students mockStudents;

    @InjectMocks
    private DefaultStudentService studentService;

    @BeforeEach
    void setUp() {
        resetMockInteractions();
    }

    private void resetMockInteractions() {
        when(mockStudents.findAll()).thenReturn(Collections.emptyList());
    }

    @Test
    void getPassedStudents() {
        Student student1 = new Student(1, "John");
        Student student2 = new Student(2, "Alice");

        student1.setScore(new Score(1, 80));
        student2.setScore(new Score(2, 40));

        when(mockStudents.findAll()).thenReturn(List.of(student1, student2));

        Collection<Student> result = studentService.getPassedStudents();

        assertNotNull(result);
    }

    @Test
    void getStudentsOrderByScore() {
        Student student1 = new Student(1, "John");
        Student student2 = new Student(2, "Alice");

        student1.setScore(new Score(1, 80));
        student2.setScore(new Score(2, 75));

        when(mockStudents.findAll()).thenReturn(List.of(student1, student2));

        Collection<Student> result = studentService.getStudentsOrderByScore();

        assertNotNull(result);
    }
}
