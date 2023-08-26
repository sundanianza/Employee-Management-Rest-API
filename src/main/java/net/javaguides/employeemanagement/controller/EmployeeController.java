package net.javaguides.employeemanagement.controller;


import io.swagger.v3.oas.annotations.Operation;
import net.javaguides.employeemanagement.model.Employee;
import net.javaguides.employeemanagement.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@Tag(name = "Employee", description = "Employee operations")
@RequestMapping("/api/v1/")
public class EmployeeController {


    private final EmployeeService employeeService;

    @Autowired
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }


    //get all Employees





    @PostMapping ("/employees")
    @Operation(summary = "Create Employee")
    public Employee createEmployee(@RequestBody Employee employee){
        System.out.println("Received request to create employee: " + employee);


        return employeeService.createEmployee(employee);
    }
    @GetMapping("/employees")
    @Operation(summary = "Get a list of all Emp")
    public List<Employee> getAllEmployees(){

        return employeeService.getAllEmployees();
    }



    @PutMapping("/employees/{employeeId}")
    @Operation(summary = "update an employee by ID")
    public ResponseEntity<Employee> updateEmployee(@PathVariable Long employeeId
    , @RequestBody Employee updatedEmployee){

        Employee updated = employeeService.updateEmployee(employeeId, updatedEmployee);


        return new ResponseEntity<>(updated, HttpStatus.OK);


    }


    @GetMapping("/employees/{employeeId}")
    @Operation(summary = "get employee by ID")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable Long employeeId){
       Optional<Employee> employee = employeeService.getEmployeeById(employeeId);

       return employee.map(value->new ResponseEntity<>(value,HttpStatus.OK))
               .orElseGet(()-> new ResponseEntity<>(HttpStatus.NOT_FOUND));



    }



    @DeleteMapping("/employees/{employeeId}")
    @Operation(summary = "Delete employee")
    public ResponseEntity<String> deleteEmployee(@PathVariable Long employeeId){
         employeeService.deleteEmployee(employeeId);

         return ResponseEntity.ok("Employee Deleted Successfully!");
    }


}
