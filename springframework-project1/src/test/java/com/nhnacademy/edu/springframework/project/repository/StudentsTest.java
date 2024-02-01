package com.nhnacademy.edu.springframework.project.repository;

import com.nhnacademy.edu.springframework.project.service.Student;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.shadow.com.univocity.parsers.csv.Csv;

import java.util.Collection;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class StudentsTest {

    @Test
    void load() {
        Students students = CsvStudents.getInstance();
        students.load();
        assertNotNull(students.findAll());
    }

    @Test
    void findAll() {
        Students students = CsvStudents.getInstance();
        students.load();
        Collection<Student> allStudents = students.findAll();

        assertNotNull(allStudents);
        assertFalse(allStudents.isEmpty());
    }

    @Test
    void merge() {
        Students students = CsvStudents.getInstance();
        students.load();

        Scores scores = CsvScores.getInstance();
        scores.load();

        students.merge(scores.findAll());

        for (Student student : students.findAll()) {
            assertNotNull(student.getScore(), "Score should not be null after merging.");
        }
    }
}