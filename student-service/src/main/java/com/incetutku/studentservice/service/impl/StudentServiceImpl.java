package com.incetutku.studentservice.service.impl;

import com.incetutku.studentservice.model.Student;
import com.incetutku.studentservice.repository.StudentRepository;
import com.incetutku.studentservice.service.StudentService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;

    public StudentServiceImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public Student save(Student student) {
        return studentRepository.save(student);
    }

    @Override
    public List<Student> findAllStudents() {
        return studentRepository.findAll();
    }
}
