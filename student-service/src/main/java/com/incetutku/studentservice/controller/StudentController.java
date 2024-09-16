package com.incetutku.studentservice.controller;

import com.incetutku.studentservice.model.Student;
import com.incetutku.studentservice.service.StudentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/students")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping
    public ResponseEntity<Student> createStudent(@RequestBody Student student) {
        return new ResponseEntity<>(studentService.save(student), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Student>> getAllStudents() {
        return ResponseEntity.ok(studentService.findAllStudents());
    }
}
