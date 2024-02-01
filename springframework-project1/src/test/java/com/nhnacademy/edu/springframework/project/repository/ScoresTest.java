package com.nhnacademy.edu.springframework.project.repository;

import com.nhnacademy.edu.springframework.project.config.ServiceConfig;
import com.nhnacademy.edu.springframework.project.repository.Scores;
import com.nhnacademy.edu.springframework.project.repository.CsvScores;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = ServiceConfig.class)
class ScoresTest {

    @Autowired
    private CsvScores scores;

    @Test
    void load() {
        scores.load();
        assertNotNull(scores.findAll());
    }

    @Test
    void findAll() {
        scores.load();
        List<Score> allScores = scores.findAll();

        assertNotNull(allScores);
        assertFalse(allScores.isEmpty());
    }
}
