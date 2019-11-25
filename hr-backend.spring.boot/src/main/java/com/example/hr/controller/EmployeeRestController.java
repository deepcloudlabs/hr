package com.example.hr.controller;


import com.example.hr.entity.Employee;
import com.example.hr.service.EmployeeService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.annotation.RequestScope;

import java.util.List;

@RestController
@RequestScope
@RequestMapping("employees")
@Api(value = "employees", description = "Endpoint for employee management")
@CrossOrigin("*")
public class EmployeeRestController {
    @Autowired
    private EmployeeService employeeService;

    @GetMapping
    @ApiOperation(value = "Returns all employees with details", notes = "Returns a complete list of employees.", response = List.class)
    @ApiResponses(value = {
       @ApiResponse(code = 200, message = "Successful retrieval of user detail", response = List.class)
    })
    public List<Employee> employees() {
        return employeeService.findAll();
    }

    @GetMapping("{identity}")
    @ApiOperation(value = "Returns an employee details", notes = "Returns employee details for a given identity no.", response = Employee.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successful retrieval of employee detail", response = Employee.class),
            @ApiResponse(code = 404, message = "Employee with given identity does not exist")
    })
    public Employee employeeByIdentity(@PathVariable @ApiParam("Identity no") String identity) {
        return employeeService.findByIdentity(identity);
    }

    @PostMapping
    @ApiOperation(value = "Creates an employee", notes = "Returns employee details of the created employee.", response = Employee.class)
    public Employee createEmployee(@RequestBody Employee employee) {
        return employeeService.addEmployee(employee);
    }

    @PutMapping
    @ApiOperation(value = "Updates an employee", notes = "Returns employee details of the updates employee.", response = Employee.class)
    public Employee updateEmployee(@RequestBody Employee employee) {
        return employeeService.updateEmployee(employee);
    }

    @DeleteMapping("{identity}")
    @ApiOperation(value = "Removes an employee", notes = "Returns employee details of the removed employee.", response = Employee.class)
    public Employee removeByIdentity(@PathVariable @ApiParam("Identity no") String identity) {
        return employeeService.removeEmployeeByIdentity(identity);
    }
}
