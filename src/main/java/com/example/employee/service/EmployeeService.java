package com.example.employee.service;

import com.example.employee.model.Employee;
import com.example.employee.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
public class EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;

    public void addEmployee(String fullName, LocalDate birthDate, String gender) {
        Employee employee = new Employee(fullName, birthDate, gender);
        employeeRepository.save(employee);
        System.out.println("Сотрудник успешно добавлен");
    }

    public List<Employee> getAllEmployees() {
        return employeeRepository.findAllByOrderByFullNameAsc();
    }

    public List<Employee> getMaleEmployeesWithF() {
        return employeeRepository.findByGenderAndFullNameStartingWith("Male", "F");
    }

    public void generateRandomData() {
        Random random = new Random();
        List<Employee> employees = new ArrayList<>();

        for (int i = 0; i < 1_000_000; i++) {
            String fullName = generateRandomName(random);
            LocalDate birthDate = generateRandomBirthDate(random);
            String gender = random.nextBoolean() ? "Male" : "Female";
            employees.add(new Employee(fullName, birthDate, gender));
        }

        for (int i = 0; i < 100; i++) {
            String fullName = "F" + generateRandomName(random).substring(1);
            LocalDate birthDate = generateRandomBirthDate(random);
            employees.add(new Employee(fullName, birthDate, "Male"));
        }

        employeeRepository.saveAll(employees);
        System.out.println("1 000 000 случайных записей и 100 записей о мужчинах с буквой 'F' вставлены");
    }

    private String generateRandomName(Random random) {
        String[] firstNames = {"Ivan", "Petr", "Sergey", "Fedor", "Alexandr", "Maksim"};
        String[] lastNames = {"Ivanov", "Petrov", "Sidorov", "Fedorov", "Alexandrov", "Maksimov"};
        String[] middleNames = {"Ivanovich", "Petrovich", "Sidorovich", "Fedorovich", "Alexandrovich", "Maksimovich"};
        String firstName = firstNames[random.nextInt(firstNames.length)];
        String lastName = lastNames[random.nextInt(lastNames.length)];
        String middleName = middleNames[random.nextInt(middleNames.length)];
        return lastName + " " + firstName + " " + middleName;
    }

    private LocalDate generateRandomBirthDate(Random random) {
        int year = 1970 + random.nextInt(70);
        int month = 1 + random.nextInt(12);
        int day = 1 + random.nextInt(28);
        return LocalDate.of(year, month, day);
    }
}
