[comment]: <> ( [!NOTE])

[comment]: <> ( Useful information that users should know, even when skimming content.)

| Annotation | Use|
|-----:|-----------|
|     @Component| annotation indicates that this class is a Spring-managed component. This means Spring will automatically detect and register this class as a bean in the Spring application context during component scanning.|
|     @Autowired| annotation on the constructor tells Spring to use this constructor when creating an instance of MyService. When Spring creates an instance of MyService, it will automatically provide an instance of MyRepository as an argument to this constructor.    |
|     @Bean     | An object managed by the Spring IoC container . Bean Lifecycle Phases => Instantiation-> Dependecy Injection -> Initialization -> Usage -> Destruction    |
|     @Configuration    | annotation indicates that a class declares one or more @Bean methods and may be processed by the Spring container to generate bean definitions and service requests for those beans at runtime.    |
|     @RestController    | It tells Spring that this class will handle web requests. When a request comes in, this class will process it and send back a response.  |
|     @GetMapping    | It tells Spring that a specific method should handle HTTP GET requests.GET requests are usually used to retrieve data from the server.|
|     @Data    |Automatically define the getter and setter also contrustor in DTO & Entity |
|     @Entity    | |
|     @Table()    |Define the name of Table in DB |
|     @Id  //primary key  |Id is an Primary Key|
|  @GeneratedValue(strategy = GenerationType.AUTO)  |Automatically generate random and unique keys|

## SpringBoot
@Id  //primary key
@GeneratedValue(strategy = GenerationType.AUTO)
#### Spring + Autoconfiguration = SpringBoot ( 2014 )

#### 1. Autoconfiguration

#### 2. Enbedded Server

#### 3. External Configuration

## Dependecies Injection

#### Dependency Injection is a fundamental aspect of the Spring framework, through which the Spring container “injects” objects into other objects or “dependencies”.

#### This allows for loose coupling of components and moves the responsibility of managing components onto the container.

#### Inversion of Control having responsibility to inject Dependencies.

#### Ex. Bakery shop (we can only change ingridents not whole machines)

#### Connecting objects with other objects, or “injecting” objects into other objects,

### Inversion of Control

#### Inversion of Control is a principle in software engineering which transfers the control of objects or portions of a program to a container or framework. We most often use it in the context of object-oriented programming.

``` java
// Define a service with a dependency
@Service
public class MyService {
    private final MyRepository myRepository;

    @Autowired
    public MyService(MyRepository myRepository) {
        this.myRepository = myRepository;
    }

    public void performAction() {
        myRepository.save();
    }
}

// Define a repository
@Repository
public class MyRepository {
    public void save() {
        // save logic
    }
}
// In this example, Spring manages the creation and injection of MyRepository into MyService.
```

### Advantage of this IOC

##### decoupling the execution of a task from its implementation

##### making it easier to switch between different implementations

##### greater modularity of a program

##### greater ease in testing a program by isolating a component or mocking its dependencies, and allowing components to communicate through contracts

## Use MVC

### Model, View, Controller

#### 1. Presentation Layer => Define @Controllers

#### 2. Service Layer(Logic Layer)

#### 3. Persistance Layer (contact with DB)

### Advantanges => Scalability , Maintainability,Testability

### Create Packages

#### 1. controllers

#### 2. services

#### 3. repositories

#### 4. dto

#### 5. entities

## 1. How to Use @RestController and @GetMapping

### Create a Spring Boot Application:

##### First, you need to set up a Spring Boot project. You can use Spring Initializr or your preferred method to create a Spring Boot application.

### Create a Controller Class:

##### Inside your project, create a new Java class and annotate it with @RestController.

### Define a Method to Handle GET Requests:

##### Inside this class, create a method and annotate it with @GetMapping. This method will handle GET requests.

## how data Transfer

#### Presentation <-----DTO------> Service<------Entity------->Presestense Layer
#### //Controller <- service <- Repository
### Jackson(Library) => Jackson is responsible for the convert data to and from JSON;

## DB Connection

##### H2 Database (Dependency)

```xml 

[comment]: <> (pom.xml)
<dependency>
	<groupId>org.springframework.boot</groupId>
	<artifactId>spring-boot-starter-data-jpa</artifactId>
</dependency>

<dependency>
	<groupId>com.h2database</groupId>
	<artifactId>h2</artifactId>
</dependency>

``` 
``` java 

//EmployeeEntity.java
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

//persistence layer is Done.(created)   wait....something went wrong
		
```

```java 
//EmployeeRepository
package com.codingshuttle.AnujTutorial.Tutorial.repositories;

import com.codingshuttle.AnujTutorial.Tutorial.entities.EmployeeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

//at a starting time , we need to create all create operation queries but now there is a method which having already mention all operation , we just need to implement and use
//JpaRepository (contains all CRUD operations), inside JpaRepository there is intresting stuff;

@Repository
public interface EmployeeRepository extends JpaRepository<EmployeeEntity,Long> {
}
 //persistanse layer is done.
 
 //OR
 
 // IMP
 // If we want to write our own query (complex query) so we can write as
  public interface UserRepository extends JpaRepository<User, Long> {

    // JPQL query
    @Query("SELECT u FROM User u WHERE u.email = :email")
    User findByEmail(@Param("email") String email);

    // Native SQL query
    @Query(value = "SELECT * FROM users u WHERE u.email = :email", nativeQuery = true)
    User findByEmailNative(@Param("email") String email);
}
```
```java 

//Service
package com.codingshuttle.AnujTutorial.Tutorial.services;


import com.codingshuttle.AnujTutorial.Tutorial.dto.EmployeeDTO;
import com.codingshuttle.AnujTutorial.Tutorial.entities.EmployeeEntity;
import com.codingshuttle.AnujTutorial.Tutorial.repositories.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

//Service layer convert DTO to Entity and Entity to DTO
@Service
public class EmployeeServices {
//we get EmployeeRepository Ojb by using Dependency Injection
//    @Autowired
    //we use contructor injection
    final EmployeeRepository employeeRepository; // we always keep it final, so we need to initialize it . thus we create constructor;

    public EmployeeServices(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public EmployeeDTO getEmployeeById(Long id){
       EmployeeEntity employeeEntity= employeeRepository.getReferenceById(id); // this data is an entity , so we need to convert to DTO because this method return DTO
       return new EmployeeDTO(employeeEntity.getId(),employeeEntity.getName(),employeeEntity.getDateOfJoining(),employeeEntity.isIsactive()); //convert Entity to DTO


    }
}


```

```java 

//controller
package com.codingshuttle.AnujTutorial.Tutorial.controllers;


import com.codingshuttle.AnujTutorial.Tutorial.dto.EmployeeDTO;
import com.codingshuttle.AnujTutorial.Tutorial.services.EmployeeServices;
import jakarta.websocket.server.PathParam;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Date;

// operations
//GET /employees
//POST /employees
//DELETE /employees/{id}
@RestController
public class EmployeeController {
//Controller <- service <- Repository    ----------data flow
    private final EmployeeServices employeeServices; // we need obj of empservice to get data so we use  Dependency Injection by creating Constructor injection


    public EmployeeController(EmployeeServices employeeServices) {
        this.employeeServices = employeeServices;
    }

    @GetMapping(path = "/employees/{id}")
    public EmployeeDTO getEmployeeById(@PathVariable("id") Long employeeId) {
        return employeeServices.getEmployeeById(employeeId);
    }
    }
```

### Conversion

#### if we want to get data from db(get req) then we need to convert Entity  to DTO 
```java 
 public EmployeeDTO getEmployeeById(Long id){
       EmployeeEntity employeeEntity= employeeRepository.getReferenceById(id); // this data is an entity , so we need to convert to DTO because this method return DTO
       return new EmployeeDTO(employeeEntity.getId(),employeeEntity.getName(),employeeEntity.getDateOfJoining(),employeeEntity.isIsactive()); //convert Entity to DTO
 }
```

### if we want to save data to db then we need to convert DTO to Entity,

```java 
public EmployeeEntity saveEmployee(EmployeeDTO employeeDTO) {
    EmployeeEntity employeeEntity = new EmployeeEntity(); // create new Entity instance
    employeeEntity.setName(employeeDTO.getName()); // set Entity fields from DTO
    employeeEntity.setDateOfJoining(employeeDTO.getDateOfJoining());
    employeeEntity.setIsActive(employeeDTO.isIsActive());
    return employeeRepository.save(employeeEntity); // save Entity to the database
}
```
### But this is repetadely use process so there is an dependency for this conversion.
#### **********ModelMapper ***********
```xml 
<dependency>
  <groupId>org.modelmapper</groupId>
  <artifactId>modelmapper</artifactId>
  <version>3.0.0</version>
</dependency>
```
#### After install dependency we just create Bean of ModelMapper in configuration class
```xml 
  @Bean
   public ModelMapper getModelMapper(){
       return new ModelMapper();
   }
```

```java
 modelMapper.map(employeeDTO, EmployeeEntity.class); //dto to entity
```
``` java
 modelMapper.map(employeeEntity, EmployeeDTO.class); //entity to dto
```
