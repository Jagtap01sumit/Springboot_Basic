package com.codingshuttle.AnujTutorial.Tutorial.services;

//Working
//conversion of dto to entity and entity to dto

import com.codingshuttle.AnujTutorial.Tutorial.dto.EmployeeDTO;
import com.codingshuttle.AnujTutorial.Tutorial.entities.EmployeeEntity;
import com.codingshuttle.AnujTutorial.Tutorial.repositories.EmployeeRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

//Service layer convert DTO to Entity and Entity to DTO
@Service
public class EmployeeServices {
    //we get EmployeeRepository Ojb by using Dependency Injection
//    @Autowired
    //we use contructor injection
    final EmployeeRepository employeeRepository; // we always keep it final, so we need to initialize it . thus we create constructor;

    final ModelMapper modelMapper;


    public EmployeeServices(EmployeeRepository employeeRepository, ModelMapper modelMapper) {
        this.employeeRepository = employeeRepository;
        this.modelMapper = modelMapper;
    }

    //entity-> dto
    public EmployeeDTO getEmployeeById(Long id) {
        EmployeeEntity employeeEntity = employeeRepository.getById(id); // this data is an entity , so we need to convert to DTO because this method return DTO
//       return new EmployeeDTO(employeeEntity.getId(),employeeEntity.getName(),employeeEntity.getDateOfJoining(),employeeEntity.isIsactive()); //convert Entity to DTO
        return modelMapper.map(employeeEntity, EmployeeDTO.class);

    }

    //to save data to db.....DTO->Entity
    public EmployeeDTO createNewEmployee(EmployeeDTO employeeDTO) {
        EmployeeEntity employeeEntity = modelMapper.map(employeeDTO, EmployeeEntity.class);
        return modelMapper.map(employeeRepository.save(employeeEntity), EmployeeDTO.class);

    }

    public List<EmployeeDTO> getAllEmployees() {
        List<EmployeeDTO> list = new ArrayList<>();
        for (EmployeeEntity employeeEntity : employeeRepository.findAll()) {
            EmployeeDTO map =

                    modelMapper.map(employeeEntity, EmployeeDTO.class);
            list.add(map);
        }
        return list;
    }
}
