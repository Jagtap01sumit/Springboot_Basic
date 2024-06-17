package com.codingshuttle.AnujTutorial.Tutorial.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Date;

@Data //its automatically set getters and setters
@AllArgsConstructor // Lombok annotation to generate an all-arguments constructor
@NoArgsConstructor // Lombok annotation to generate a no-arguments constructor
public class EmployeeDTO {

    private Long Id;
    private String name;
    private LocalDate dateOfJoining;
    @JsonProperty("isActive")
    private boolean isactive;


}
