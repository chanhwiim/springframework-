package com.nhnacademy.edu.springframework.project.service;

import com.nhnacademy.edu.springframework.project.config.ServiceConfig;
import com.nhnacademy.edu.springframework.project.service.DataLoadService;
import com.nhnacademy.edu.springframework.project.service.CsvDataLoadService;
import com.nhnacademy.edu.springframework.project.repository.Scores;
import com.nhnacademy.edu.springframework.project.repository.Students;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import static org.mockito.Mockito.verify;

@SpringBootTest(classes = ServiceConfig.class)
class DataLoadServiceTest {

    @Mock
    private Scores mockScores;

    @Mock
    private Students mockStudents;

    @InjectMocks
    private DataLoadService dataLoadService = new CsvDataLoadService();

    @Test
    void loadAndMerge() {
        dataLoadService.loadAndMerge();

        verify(mockScores).load();
        verify(mockStudents).load();
        verify(mockStudents).merge(Mockito.anyList());
    }
}
