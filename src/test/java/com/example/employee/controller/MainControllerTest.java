package com.example.employee.controller;

import com.example.employee.service.EmployeeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.CommandLineRunner;

import static org.mockito.Mockito.*;
import java.time.LocalDate;

class MainControllerTest {
    @Mock
    private EmployeeService employeeService;

    @InjectMocks
    private MainController mainController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testRunMode2() throws Exception {

        String[] args = {"2", "Ivanov Petr Ivanovich", "1990-01-01", "Male"};

        CommandLineRunner runner = mainController;
        runner.run(args);

        verify(employeeService, times(1)).addEmployee("Ivanov Petr Ivanovich", LocalDate.of(1990, 1, 1), "Male");
    }

    @Test
    void testRunInvalidMode() throws Exception {

        String[] args = {"10"};

        CommandLineRunner runner = mainController;
        runner.run(args);

        verify(employeeService, never()).addEmployee(anyString(), any(), anyString());
    }
}
