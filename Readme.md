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

## SpringBoot 
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

### Jackson(Library) => Jackson is responsible for the convert data to and from JSON;

## DB Connection

