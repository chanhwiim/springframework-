package com.nhnacademy.edu.springframework.project.service;

import com.nhnacademy.edu.springframework.project.repository.CsvStudents;
import com.nhnacademy.edu.springframework.project.repository.Score;
import com.nhnacademy.edu.springframework.project.repository.Scores;
import com.nhnacademy.edu.springframework.project.repository.Students;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class DefaultGradeQueryService implements GradeQueryService {

    private final Students students;
    private final Scores scores;

    public DefaultGradeQueryService(Students students, Scores scores) {
        this.students = students;
        this.scores = scores;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DefaultGradeQueryService that = (DefaultGradeQueryService) o;
        return Objects.equals(students, that.students) && Objects.equals(scores, that.scores);
    }

    @Override
    public int hashCode() {
        return Objects.hash(students, scores);
    }

    @Override
    public List<Score> getScoreByStudentName(String name) {
        // TODO 5: 학생 이름으로 점수를 반환합니다. 동명이인 고려합니다. cle

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
        // TODO 6 : 학번으로 점수를 반환합니다. seq 인자가 학번입니다. cle
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
