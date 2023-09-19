package net.javaguides.employeemanagement;

import net.javaguides.employeemanagement.model.Employee;
import net.javaguides.employeemanagement.repository.EmployeeRepository;
import net.javaguides.employeemanagement.service.EmployeeService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;


@SpringBootTest
class EmployeeManagementApplicationTests {

    @Autowired
    private EmployeeService employeeService;

    @MockBean
    private EmployeeRepository employeeRepository;

    @Test
    void contextLoads() {
    }

    @Test
    public void getAllEmployees(){
        when(employeeRepository.findAll()).thenReturn(Stream
                .of(new Employee("Anzani","Sundani","1"),
                        new Employee("Happy","Luvhengo","2"))
                .collect(Collectors.toList()));
        assertEquals(2,employeeService.getAllEmployees().size());


    }

    @Test
    public void testCreateEmployee(){
        //create a sample employee object

        Employee employee = new Employee();
        employee.setEmailId("1");
        employee.setFirstName("Anzani");
        employee.setLastName("Sundani");

        // Mock the behavior of the repository

        when(employeeRepository.save(any(Employee.class))).thenReturn(employee);

        // Call the method under test

        Employee createdEmployee = employeeService.createEmployee((employee));

        // Verify that the save method of the repository was called once with the provided employee

        verify(employeeRepository,times(1)).save(eq(employee));

        // Assert that the returned employee matches the one provided

        assertEquals(employee,createdEmployee);


    }

    @Test
    public void testUpdateEmployee() {
        Employee initialEmployee = new Employee();
       initialEmployee.setId(1L);
        initialEmployee.setFirstName("Anzani");
        initialEmployee.setLastName("Sundani");
        initialEmployee.setEmailId("sundanianza@gmail.com");

        // Create an updated employee
        Employee updatedEmployee = new Employee();
       updatedEmployee.setId(1L);
        updatedEmployee.setFirstName("Linde");
        updatedEmployee.setLastName("Sundani");
        updatedEmployee.setEmailId("sundanianza@gmail.com"); // Corrected email ID assignment

        // Mock the behavior of the repository to return the updated employee
        when(employeeRepository.save(any(Employee.class))).thenReturn(updatedEmployee);
        when(employeeRepository.findById(eq(1L))).thenReturn(Optional.of(initialEmployee));

        // Call the method under test
        Employee result = employeeService.updateEmployee(1L, updatedEmployee);

        // Verify that the repository's save method was called once with the updated employee
        verify(employeeRepository, times(1)).save(eq(updatedEmployee));

        // Verify that the repository's findById method was called once with the correct ID
        verify(employeeRepository, times(1)).findById(eq(1L));

        // Assert that the result matches the updated employee
        assertEquals(result, updatedEmployee);
    }


}
