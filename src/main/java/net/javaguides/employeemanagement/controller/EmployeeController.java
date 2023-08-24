package net.javaguides.employeemanagement.controller;

import io.swagger.annotations.ApiOperation;
import net.javaguides.employeemanagement.model.Employee;
import net.javaguides.employeemanagement.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/v1/")
public class EmployeeController {


    private final EmployeeService employeeService;

    @Autowired
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }


    //get all Employees

    @ApiOperation("Get a list of all employees")
    @RequestMapping("/employees")
    public List<Employee> getAllEmployees(){

        return employeeService.getAllEmployees();
    }

    @PostMapping ("/employees")
    public Employee createEmployee(@RequestBody Employee employee){

        return employeeService.createEmployee(employee);
    }


    @PutMapping("/employees/{employeeId}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable Long employeeId
    , @RequestBody Employee updatedEmployee){

        Employee updated = employeeService.updateEmployee(employeeId, updatedEmployee);


        return new ResponseEntity<>(updated, HttpStatus.OK);

    }



}
