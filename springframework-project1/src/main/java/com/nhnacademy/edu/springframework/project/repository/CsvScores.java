package com.nhnacademy.edu.springframework.project.repository;

import com.nhnacademy.edu.springframework.project.service.Student;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class CsvScores implements Scores {

    private static CsvScores instance;
    private List<String[]> scoreList;
    private CsvScores(){}

    /** TODO 2 : maybe cle
     * Java Singleton 패턴으로 getInstance() 를 구현하세요.
     **/
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

    // TODO 5 : score.csv 파일에서 데이터를 읽어 멤버 변수에 추가하는 로직을 구현하세요.
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
