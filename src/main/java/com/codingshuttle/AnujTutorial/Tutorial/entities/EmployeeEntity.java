package com.codingshuttle.AnujTutorial.Tutorial.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name="Employee")   //table name
public class EmployeeEntity {

    @Id  //primary key
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long Id;
    private String name;
    private LocalDate dateOfJoining;
    private boolean isactive;
}
