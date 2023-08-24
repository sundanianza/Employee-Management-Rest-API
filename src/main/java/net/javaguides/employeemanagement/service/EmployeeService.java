package net.javaguides.employeemanagement.service;

import net.javaguides.employeemanagement.exception.ResourceNotFoundException;
import net.javaguides.employeemanagement.model.Employee;
import net.javaguides.employeemanagement.repository.EmployeeRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.lang.module.ResolutionException;
import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {


    private final EmployeeRepository employeeRepository;


    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }


    public List<Employee> getAllEmployees(){

        return employeeRepository.findAll();
    }

    public Employee createEmployee(Employee employee){

        return employeeRepository.save(employee);
    }

    public Employee updateEmployee(Long employeeId, Employee updatedEmployee){

      Optional<Employee> existingEmployeeOptional= employeeRepository.findById(employeeId);

      if(existingEmployeeOptional.isPresent()){
          Employee existingEmployee = existingEmployeeOptional.get();
          existingEmployee.setFirstName(updatedEmployee.getFirstName());
          existingEmployee.setLastName(updatedEmployee.getLastName());
          existingEmployee.setEmailId(updatedEmployee.getEmailId());

          return employeeRepository.save(existingEmployee);

      }else{

          throw new ResourceNotFoundException("Employee with ID:"+employeeId+" is not found");

      }

    }
}
