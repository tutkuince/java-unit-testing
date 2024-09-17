package com.incetutku.studentservice.integration;

import com.incetutku.studentservice.model.Student;
import com.incetutku.studentservice.repository.StudentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class StudentRepositoryITest extends AbstractContainerBaseTest {

    @Autowired
    private StudentRepository studentRepository;

    private Student student;

    @BeforeEach
    void setUp() {
        student = Student.builder()
                .name("Tutku")
                .surname("Ince")
                .email("ti@mail.com")
                .build();
    }

    @DisplayName("JUnit test for save student operation")
    @Test
    void givenStudentObject_whenSave_thenReturnSavedStudentObject() {
        // given - precondition or setup
        // student

        // when - action or the behaviour that we are going to test
        Student savedStudent = studentRepository.save(student);

        // then - verify the output
        assertThat(savedStudent).isNotNull();
        assertThat(savedStudent.getId()).isGreaterThan(0);
    }

    @DisplayName("Junit test for find all students operation")
    @Test
    void givenStudentList_whenFindAll_thenReturnStudentList() {
        // given - precondition or setup
        Student student1 = Student.builder()
                .name("Utku")
                .surname("Ince")
                .email("ui@mail.com")
                .build();
        studentRepository.save(student);
        studentRepository.save(student1);

        // when - action or the behaviour that we are going to test
        List<Student> studentList = studentRepository.findAll();

        // then - verify the output
        assertThat(studentList).isNotNull();
        assertThat(studentList).isNotEmpty();
        assertThat(studentList.size()).isEqualTo(2);

    }
}