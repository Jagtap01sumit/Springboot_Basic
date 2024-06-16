package com.codingshuttle.AnujTutorial.Tutorial.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;
import java.util.Date;

@Data //its automatically set getters and setters
@AllArgsConstructor
public class EmployeeDTO {

    private Long Id;
    private String name;
    private LocalDate dateOfJoining;
    private boolean isactive;


}
