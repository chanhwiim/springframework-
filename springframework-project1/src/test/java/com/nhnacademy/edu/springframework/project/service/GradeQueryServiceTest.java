package com.nhnacademy.edu.springframework.project.service;

import com.nhnacademy.edu.springframework.project.repository.Score;
import com.nhnacademy.edu.springframework.project.repository.Scores;
import com.nhnacademy.edu.springframework.project.repository.Students;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class GradeQueryServiceTest {

    private Students mockStudents;
    private Scores mockScores;
    private GradeQueryService gradeQueryService;

    @BeforeEach
    void setUp() {
        mockStudents = mock(Students.class);
        mockScores = mock(Scores.class);
        gradeQueryService = new DefaultGradeQueryService(mockStudents, mockScores);
    }

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
    }

    @Test
    void getScoreByStudentName_NoMatchingName() {
        String studentName = "Alice";
        when(mockStudents.findAll()).thenReturn(Collections.emptyList());

        List<Score> result = gradeQueryService.getScoreByStudentName(studentName);

        assertEquals(0, result.size());
    }

    @Test
    void getScoreByStudentSeq() {
        int studentSeq = 1;
        Student student = new Student(studentSeq, "John");
        when(mockStudents.findAll()).thenReturn(Collections.singletonList(student));

        Score score1 = new Score(1, 90);
        Score score2 = new Score(2, 80);
        Score score3 = new Score(3, 75);
        when(mockScores.findAll()).thenReturn(Arrays.asList(score1, score2, score3));

        Score result = gradeQueryService.getScoreByStudentSeq(studentSeq);

        assertEquals(score1, result);
    }

    @Test
    void getScoreByStudentSeq_NoMatchingSeq() {
        int studentSeq = 4;
        when(mockStudents.findAll()).thenReturn(Collections.emptyList());

        Score result = gradeQueryService.getScoreByStudentSeq(studentSeq);

        assertEquals(null, result);
    }
}
