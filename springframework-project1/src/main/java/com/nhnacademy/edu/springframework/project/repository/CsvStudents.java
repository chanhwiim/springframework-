package com.nhnacademy.edu.springframework.project.repository;

import com.nhnacademy.edu.springframework.project.service.Student;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;

import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


public class CsvStudents implements Students {

    private static CsvStudents instance;
    private List<Student> studentList = new ArrayList<>();

    /**
     * TODO 3 : maybe cle
     * Java Singleton 패턴으로 getInstance() 를 구현하세요.
     **/
    public static Students getInstance() {
        if (instance == null) {
            synchronized (CsvStudents.class) {
                if (instance == null) {
                    instance = new CsvStudents();
                }
            }
        }
        return instance;
    }

    // TODO 7 : student.csv 파일에서 데이터를 읽어 클래스 멤버 변수에 추가하는 로직을 구현하세요. cle
    // 데이터를 적재하고 읽기 위해서, 적절한 자료구조를 사용하세요.
    @Override
    public void load() {
        try (CSVReader csvReader = new CSVReader(new InputStreamReader(getClass().getClassLoader().getResourceAsStream("data/student.csv")))) {
            studentList = csvReader.readAll().stream()
                    .map(array -> new Student(Integer.parseInt(array[0]), array[1]))
                    .collect(Collectors.toList());
        } catch (IOException | CsvException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Collection<Student> findAll() {
        return studentList;
    }

    /**
     * TODO 8 : students 데이터에 score 정보를 추가하세요. cle fkfkfkfkfkkfkfkfk
     *
     * @param scores
     */
    @Override
    public void merge(Collection<Score> scores) {
        for (Student student : findAll()) {
            int seq = student.getSeq();

            Optional<Score> matchingScore = scores.stream()
                    .filter(score -> score.getStudentSeq() == seq)
                    .findFirst();

            matchingScore.ifPresentOrElse(
                    score -> {
                        student.setScore(score);
                        System.out.println("Merged: " + student);
                    },
                    () -> {
                        // If no matching score is found, create a new Score with 0 and merge
                        Score zeroScore = new Score(seq, 0);
                        student.setScore(zeroScore);
                        System.out.println("Merged with 0: " + student);
                    }
            );
        }
    }
}
