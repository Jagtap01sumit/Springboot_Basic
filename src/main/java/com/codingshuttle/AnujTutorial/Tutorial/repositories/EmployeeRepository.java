package com.codingshuttle.AnujTutorial.Tutorial.repositories;

import com.codingshuttle.AnujTutorial.Tutorial.entities.EmployeeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

//at a starting time , we need to create all create operation queries but now there is a method which having already mention all operation , we just need to implement and use
//JpaRepository (contains all CRUD operations), inside JpaRepository there is intresting stuff;

@Repository
public interface EmployeeRepository extends JpaRepository<EmployeeEntity,Long> {
}
