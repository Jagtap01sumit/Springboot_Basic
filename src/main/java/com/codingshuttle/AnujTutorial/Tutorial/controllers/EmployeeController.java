package com.codingshuttle.AnujTutorial.Tutorial.controllers;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

// operations
//GET /employees
//POST /employees
//DELETE /employees/{id}
@RestController
public class EmployeeController {

    @GetMapping(path = "/employees")
    public String getEmployees(){
        return "Hello world";
    }
}
