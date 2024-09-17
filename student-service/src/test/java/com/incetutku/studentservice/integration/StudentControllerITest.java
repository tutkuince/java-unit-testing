package com.incetutku.studentservice.integration;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.incetutku.studentservice.model.Student;
import com.incetutku.studentservice.repository.StudentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class StudentControllerITest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private ObjectMapper objectMapper;

    private Student student;

    @BeforeEach
    void setUp() {
        studentRepository.deleteAll();

        student = Student.builder()
                .name("Tutku")
                .surname("Ince")
                .email("ti@mail.com")
                .build();
    }

    @DisplayName("Integration for Create Student REST API")
    @Test
    void givenStudentObject_whenCreateStudent_thenReturnSavedStudent() throws Exception {
        // given - precondition or setup
        // student

        // when - action or the behaviour that we are going to test
        ResultActions response = mockMvc.perform(post("/api/v1/students")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(student)));


        // then - verify the output
        response.andDo(print()).andExpect(status().isCreated()).andExpect(jsonPath("$.name", is(student.getName())));
        response.andDo(print()).andExpect(status().isCreated()).andExpect(jsonPath("$.surname", is(student.getSurname())));
        response.andDo(print()).andExpect(status().isCreated()).andExpect(jsonPath("$.email", is(student.getEmail())));
    }

}
