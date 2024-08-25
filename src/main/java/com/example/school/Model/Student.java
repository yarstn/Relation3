package com.example.school.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.Set;

@Entity
@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotEmpty(message = "Username cannot be null or empty")
    @Size(min = 5, message = "Username must be longer than 4 characters")
    @Column(columnDefinition = "varchar(20) not null unique")
    private String name;
    @NotNull(message = "age cannot be null or empty")
    @Column(columnDefinition = "int not null ")
    private int age;
    @NotEmpty(message = "major cannot be null or empty")
    @Size(min = 5, message = "major must be longer than 4 characters")
    @Column(columnDefinition = "varchar(20) not null ")
    private String major;

    @ManyToMany(mappedBy = "students")
    private Set<Course> courses;
}
