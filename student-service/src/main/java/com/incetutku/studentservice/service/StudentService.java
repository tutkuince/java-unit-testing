package com.incetutku.studentservice.service;

import com.incetutku.studentservice.model.Student;

import java.util.List;

public interface StudentService {
    Student save(Student student);

    List<Student> findAllStudents();
}
