package com.nhnacademy.edu.springframework.project.service;

import com.nhnacademy.edu.springframework.project.repository.CsvStudents;
import com.nhnacademy.edu.springframework.project.repository.Score;
import com.nhnacademy.edu.springframework.project.repository.Scores;
import com.nhnacademy.edu.springframework.project.repository.Students;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service("gradeGueryService")
public class DefaultGradeQueryService implements GradeQueryService {

    private Students students;
    private Scores scores;

    public DefaultGradeQueryService(Students students, Scores scores) {
        this.students = students;
        this.scores = scores;
    }
    @Override
    public List<Score> getScoreByStudentName(String name) {

        List<String> studentList = students.findAll().stream()
                .filter(student -> student.getName().equals(name))
                .map(Student::getName)
                .collect(Collectors.toList());

        return scores.findAll().stream()
                .filter(s -> studentList.contains(s.getStudentSeq()))
                .collect(Collectors.toList());
    }

    @Override
    public Score getScoreByStudentSeq(int seq) {
        List<Integer> studentList = students.findAll().stream()
                .filter(student -> student.getSeq() == seq)
                .map(Student::getSeq)
                .collect(Collectors.toList());

        List<Score> allScores = scores.findAll();

        return allScores.stream()
                .filter(s -> studentList.contains(s.getStudentSeq()))
                .findFirst()
                .orElse(null);
    }
}
