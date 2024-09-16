package com.incetutku.studentservice.repository;

import com.incetutku.studentservice.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Long> {
}
