package com.example.hr.repository;

import com.example.hr.entity.Employee;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface EmployeeRepository extends MongoRepository<Employee, Long> {
    Optional<Employee> findOneByIdentity(String identity);
}
