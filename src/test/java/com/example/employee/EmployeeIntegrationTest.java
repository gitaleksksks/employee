package com.example.employee;

import com.example.employee.model.Employee;
import com.example.employee.repository.EmployeeRepository;
import com.example.employee.service.EmployeeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@ActiveProfiles("test")
class EmployeeIntegrationTest {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private EmployeeService employeeService;

    @BeforeEach
    void setUp() {

        employeeRepository.deleteAll();
    }

    @Test
    void testAddAndGetEmployee() {

        Employee employee = new Employee("Ivanov Petr Ivanovich", LocalDate.of(1990, 1, 1), "Male");
        employeeRepository.save(employee);

        List<Employee> employees = employeeRepository.findAll();

        assertEquals(1, employees.size());
        assertEquals("Ivanov Petr Ivanovich", employees.get(0).getFullName());
    }

    @Test
    void testGenerateRandomData() {

        employeeService.generateRandomData();

        List<Employee> employees = employeeRepository.findAll();
        assertEquals(1_000_100, employees.size());
    }

    @Test
    void testGetMaleEmployeesWithF() {

        Employee employee1 = new Employee("Fedorov Fedor Fedorovich", LocalDate.of(1980, 10, 10), "Male");
        Employee employee2 = new Employee("Frolov Ivan Ivanovich", LocalDate.of(1975, 5, 5), "Male");
        employeeRepository.saveAll(List.of(employee1, employee2));

        List<Employee> employees = employeeService.getMaleEmployeesWithF();

        assertEquals(2, employees.size());
        assertEquals("Fedorov Fedor Fedorovich", employees.get(0).getFullName());
        assertEquals("Frolov Ivan Ivanovich", employees.get(1).getFullName());
    }
}
