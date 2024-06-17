package com.codingshuttle.AnujTutorial.Tutorial.controllers;


import com.codingshuttle.AnujTutorial.Tutorial.dto.EmployeeDTO;
import com.codingshuttle.AnujTutorial.Tutorial.repositories.EmployeeRepository;
import com.codingshuttle.AnujTutorial.Tutorial.services.EmployeeServices;
import jakarta.websocket.server.PathParam;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

// operations
//GET /employees
//POST /employees
//DELETE /employees/{id}
@RestController
@RequestMapping(path="employees")
public class EmployeeController {
//Controller <- service <- Repository    ----------data flow
    private final EmployeeServices employeeServices; // we need obj of empservice to get data so we use  Dependency Injection by creating Constructor injection


    public EmployeeController(EmployeeServices employeeServices) {
        this.employeeServices = employeeServices;
    }

    @GetMapping(path = "/{id}")
    public EmployeeDTO getEmployeeById(@PathVariable("id") Long employeeId) {
        return employeeServices.getEmployeeById(employeeId);
    }

    //Type to take data input
    //PathVAriable
    //PathParams
    //RequestBody

    @PostMapping
    public EmployeeDTO createNewEmployee(@RequestBody EmployeeDTO employeeDTO) {
        return employeeServices.createNewEmployee(employeeDTO);
    }
    @GetMapping
    public List<EmployeeDTO> getAllEmployees(){
        return  employeeServices.getAllEmployees();

    }

}
