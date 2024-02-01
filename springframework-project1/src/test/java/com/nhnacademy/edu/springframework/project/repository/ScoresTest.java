package com.nhnacademy.edu.springframework.project.repository;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ScoresTest {

    @Test
    void load() {
        Scores scores = CsvScores.getInstance();
        scores.load();
        assertNotNull(scores.findAll());
    }

    @Test
    void findAll() {
        Scores scores = CsvScores.getInstance();
        scores.load();
        List<Score> allScores = scores.findAll();

        assertNotNull(allScores);
        assertFalse(allScores.isEmpty());

    }
}