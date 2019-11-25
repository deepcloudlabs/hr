package com.example.hr.service;

import com.example.hr.entity.Employee;
import com.example.hr.exception.DuplicateEmployeeException;
import com.example.hr.exception.EmployeeNotFoundException;
import com.example.hr.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;

    public List<Employee> findAll() {
        return employeeRepository.findAll();
    }

    public Employee findByIdentity(final String identity) {
        final Optional<Employee> employee = employeeRepository.findOneByIdentity(identity);
        if (employee.isPresent()) return employee.get();
        throw new EmployeeNotFoundException("Employee is not found!", "a83e00d3-1907-4e1a-aa34-7a9b3b5c8ecf", "employeeNotFound", identity);
    }

    public Employee addEmployee(final Employee employee) {
        final String identity = employee.getIdentity();
        final Optional<Employee> optionalEmployee = employeeRepository.findOneByIdentity(identity);
        if (optionalEmployee.isPresent())
            throw new DuplicateEmployeeException("Employee already exists", "a41d8c25-74c3-4a7d-a22b-4d77e90726dc", "employeeAlreadyExists", identity);
        return employeeRepository.save(employee);
    }

    public Employee updateEmployee(final Employee employee) {
        final String identity = employee.getIdentity();
        final Optional<Employee> optionalEmployee = employeeRepository.findOneByIdentity(identity);
        if (!optionalEmployee.isPresent())
            throw new EmployeeNotFoundException("Employee does not exist!", "83dc9b70-3d07-4dfe-9a0f-bbeffae33d2b", "employeeNotFound", identity);
        final Employee emp = optionalEmployee.get();
        emp.setSalary(employee.getSalary());
        emp.setPhoto(employee.getPhoto());
        employeeRepository.save(emp);
        return emp;
    }

    public Employee removeEmployeeByIdentity(final String identity) {
        final Optional<Employee> optionalEmployee = employeeRepository.findOneByIdentity(identity);
        if (!optionalEmployee.isPresent())
            throw new EmployeeNotFoundException("Employee already exists", "95116408-e35a-47da-b268-ab309f7f9ea5", "employeeNotFound", identity);
        final Employee emp = optionalEmployee.get();
        employeeRepository.delete(emp);
        return emp;
    }
}