package com.nhnacademy.edu.springframework.project.service;

import com.nhnacademy.edu.springframework.project.config.ServiceConfig;
import com.nhnacademy.edu.springframework.project.repository.Score;
import com.nhnacademy.edu.springframework.project.repository.Students;
import com.nhnacademy.edu.springframework.project.repository.Scores;
import com.nhnacademy.edu.springframework.project.service.GradeQueryService;
import com.nhnacademy.edu.springframework.project.service.DefaultGradeQueryService;
import com.nhnacademy.edu.springframework.project.service.Student;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
class GradeQueryServiceTest {

    @Mock
    private Students mockStudents;

    @Mock
    private Scores mockScores;

    @InjectMocks
    private DefaultGradeQueryService gradeQueryService;

    @Test
    void name() {
        gradeQueryService.hashCode();
    }

    @Test
    void getScoreByStudentName() {
        String studentName = "John";
        Student student1 = new Student(1, studentName);
        Student student2 = new Student(2, studentName);

        // Stubbing for mockStudents
        when(mockStudents.findAll()).thenReturn(Arrays.asList(student1, student2));

        Score score1 = new Score(1, 90);

        // Stubbing for mockScores
        when(mockScores.findAll()).thenReturn(List.of(score1));

        List<Score> result = gradeQueryService.getScoreByStudentName(studentName);
        System.out.println(result);

        assertNotNull(result);
    }
}