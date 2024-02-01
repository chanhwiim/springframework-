// StudentServiceTest.java
package com.nhnacademy.edu.springframework.project.service;

import com.nhnacademy.edu.springframework.project.repository.CsvStudents;
import com.nhnacademy.edu.springframework.project.repository.Score;
import com.nhnacademy.edu.springframework.project.repository.Students;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Collection;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

class StudentServiceTest {

    private Students mockStudents;
    private DefaultStudentService studentService;

    @BeforeEach
    void setUp() {
        mockStudents = Mockito.mock(Students.class);
        studentService = new DefaultStudentService();
    }

    @Test
    void getPassedStudents() {
        // Arrange
        Student student1 = new Student(1, "John");
        Student student2 = new Student(2, "Alice");

        // Assuming student1 passed and student2 failed
        student1.setScore(new Score(1, 80));
        student2.setScore(new Score(2, 40)); // Assuming a failing score

        when(mockStudents.findAll()).thenReturn(List.of(student1, student2));

        // Act
        Collection<Student> result = studentService.getPassedStudents();

        assertNotNull(result);
    }

    @Test
    void getStudentsOrderByScore() {
        // Arrange
        Student student1 = new Student(1, "John");
        Student student2 = new Student(2, "Alice");

        // Assuming scores for the students
        student1.setScore(new Score(1, 80));
        student2.setScore(new Score(2, 75));

        when(mockStudents.findAll()).thenReturn(List.of(student1, student2));

        // Act
        Collection<Student> result = studentService.getStudentsOrderByScore();

        assertNotNull(result);
    }

}
