package com.nhnacademy.edu.springframework.project.service;

import com.nhnacademy.edu.springframework.project.repository.CsvScores;
import com.nhnacademy.edu.springframework.project.repository.CsvStudents;
import com.nhnacademy.edu.springframework.project.repository.Scores;
import com.nhnacademy.edu.springframework.project.repository.Students;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service("csvDataLoadService")
public class CsvDataLoadService implements DataLoadService {

    private Scores scores;
    private Students students;

    public CsvDataLoadService() {
    }

    public CsvDataLoadService(Scores scores, Students students) {
        this.scores = scores;
        this.students = students;
    }

    @Override
    public void loadAndMerge() {
//        Scores scores = CsvScores.getInstance(); //테스트시 주석처리.
        scores.load();

//        Students students = CsvStudents.getInstance(); //테스트시 주석처리.
        students.load();
        students.merge(scores.findAll());
    }
}
