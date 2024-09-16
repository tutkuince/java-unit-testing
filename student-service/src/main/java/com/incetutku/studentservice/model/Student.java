package com.incetutku.studentservice.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Student {
    @Id
    private long id;
    private String name;
    private String surname;
    private String email;
}
