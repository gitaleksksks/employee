package com.example.employee.service;

import com.example.employee.model.Employee;
import com.example.employee.repository.EmployeeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class EmployeeServiceTest {
    @Mock
    private EmployeeRepository employeeRepository;

    @InjectMocks
    private EmployeeService employeeService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testAddEmployee() {

        String fullName = "Ivanov Petr Ivanovich";
        LocalDate birthDate = LocalDate.of(1990, 1, 1);
        String gender = "Male";

        employeeService.addEmployee(fullName, birthDate, gender);

        verify(employeeRepository, times(1)).save(any(Employee.class));
    }

    @Test
    void testGetAllEmployees() {

        Employee employee1 = new Employee("Ivanov Petr Ivanovich", LocalDate.of(1990, 1, 1), "Male");
        Employee employee2 = new Employee("Petrov Ivan Petrovich", LocalDate.of(1985, 5, 15), "Male");
        when(employeeRepository.findAllByOrderByFullNameAsc()).thenReturn(Arrays.asList(employee1, employee2));

        List<Employee> employees = employeeService.getAllEmployees();

        assertEquals(2, employees.size());
        assertEquals("Ivanov Petr Ivanovich", employees.get(0).getFullName());
    }

    @Test
    void testGetMaleEmployeesWithF() {

        Employee employee = new Employee("Fedorov Fedor Fedorovich", LocalDate.of(1980, 10, 10), "Male");
        when(employeeRepository.findByGenderAndFullNameStartingWith("Male", "F"))
                .thenReturn(List.of(employee));

        List<Employee> employees = employeeService.getMaleEmployeesWithF();

        assertEquals(1, employees.size());
        assertEquals("Fedorov Fedor Fedorovich", employees.get(0).getFullName());
    }
}
