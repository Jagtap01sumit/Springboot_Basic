package com.codingshuttle.AnujTutorial.Tutorial.controllers;


import com.codingshuttle.AnujTutorial.Tutorial.dto.EmployeeDTO;
import jakarta.websocket.server.PathParam;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.Date;

// operations
//GET /employees
//POST /employees
//DELETE /employees/{id}
@RestController
public class EmployeeController {

    @GetMapping(path = "/employees/{id}")
    public EmployeeDTO getEmployees(@PathVariable("id") Long employeeId) {
        return new EmployeeDTO(employeeId, "sumit", LocalDate.of(2024, 01, 02), true);
    }

    @GetMapping(path = "/employees")
    public String getData(@PathParam("sortBy") String sortBy, @PathParam("limit") Integer limit) {
        return "Hello " + sortBy + " " + limit;
    }
}
