package com.nhnacademy.edu.springframework.project.service;

import com.nhnacademy.edu.springframework.project.repository.Scores;
import com.nhnacademy.edu.springframework.project.repository.Students;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.mockito.Mockito.verify;

class DataLoadServiceTest {

    @Test
    void loadAndMerge() {
        Scores mockScores = Mockito.mock(Scores.class);
        Students mockStudents = Mockito.mock(Students.class);

        DataLoadService dataLoadService = new CsvDataLoadService(mockScores, mockStudents);
        dataLoadService.loadAndMerge();

        verify(mockScores).load();
        verify(mockStudents).load();
        verify(mockStudents).merge(Mockito.anyList());
    }
}
