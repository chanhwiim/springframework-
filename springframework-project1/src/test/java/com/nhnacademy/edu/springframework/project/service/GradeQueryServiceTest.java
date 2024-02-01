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
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@SpringBootTest(classes = ServiceConfig.class)
class GradeQueryServiceTest {

    @Autowired
    private Students mockStudents;

    @Autowired
    private Scores mockScores;

    @Autowired
    private GradeQueryService gradeQueryService;

    @Test
    void getScoreByStudentName() {
        String studentName = "John";
        Student student1 = new Student(1, studentName);
        Student student2 = new Student(2, studentName);
        when(mockStudents.findAll()).thenReturn(Arrays.asList(student1, student2));

        Score score1 = new Score(1, 90);
        Score score2 = new Score(2, 80);
        Score score3 = new Score(3, 75);
        when(mockScores.findAll()).thenReturn(Arrays.asList(score1, score2, score3));

        List<Score> result = gradeQueryService.getScoreByStudentName(studentName);

        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals(score1, result.get(0));
        assertEquals(score2, result.get(1));
    }
}