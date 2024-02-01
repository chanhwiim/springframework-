package com.nhnacademy.edu.springframework.project.repository;

import com.nhnacademy.edu.springframework.project.service.Student;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CsvScores implements Scores {

    private static CsvScores instance;
    private List<String[]> scoreList;
    private CsvScores(){}

    public static Scores getInstance() {
        if (instance == null) {
            synchronized (CsvScores.class) {
                if (instance == null) {
                    instance = new CsvScores();
                }
            }
        }
        return instance;
    }
    @Override
    public void load() {

        try (CSVReader csvReader = new CSVReader(new InputStreamReader(getClass().getClassLoader().getResourceAsStream("data/score.csv")))){
            scoreList = csvReader.readAll();
        } catch (IOException | CsvException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Score> findAll() {
        return scoreList.stream()
                .map(array -> new Score(Integer.parseInt(array[0]), Integer.parseInt(array[1])))
                .collect(Collectors.toList());
    }
}
