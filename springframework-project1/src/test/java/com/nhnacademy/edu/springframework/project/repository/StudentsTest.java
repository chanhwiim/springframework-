package com.nhnacademy.edu.springframework.project.repository;

import com.nhnacademy.edu.springframework.project.config.ServiceConfig;
import com.nhnacademy.edu.springframework.project.repository.Students;
import com.nhnacademy.edu.springframework.project.repository.Scores;
import com.nhnacademy.edu.springframework.project.repository.CsvStudents;
import com.nhnacademy.edu.springframework.project.service.Student;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Collection;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = ServiceConfig.class)
class StudentsTest {

    @Autowired
    private Students students;

    @Autowired
    private Scores scores;

    @Test
    void load() {
        students.load();
        assertNotNull(students.findAll());
    }

    @Test
    void findAll() {
        students.load();
        Collection<Student> allStudents = students.findAll();

        assertNotNull(allStudents);
        assertFalse(allStudents.isEmpty());
    }

    @Test
    void merge() {
        students.load();
        scores.load();

        students.merge(scores.findAll());

        for (Student student : students.findAll()) {
            assertNotNull(student.getScore(), "Score should not be null after merging.");
        }
    }
}
