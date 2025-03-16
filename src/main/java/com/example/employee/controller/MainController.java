package com.example.employee.controller;

import com.example.employee.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class MainController implements CommandLineRunner {
    @Autowired
    private EmployeeService employeeService;

    @Override
    public void run(String... args) throws Exception {
        if (args.length == 0) {
            printUsage();
            return;
        }

        int mode = Integer.parseInt(args[0]);

        switch (mode) {
            case 2:
                String fullName = args[1];
                LocalDate birthDate = LocalDate.parse(args[2]);
                String gender = args[3];
                employeeService.addEmployee(fullName, birthDate, gender);
                break;
            case 3:
                employeeService.getAllEmployees().forEach(System.out::println);
                break;
            case 4:
                employeeService.generateRandomData();
                break;
            case 5:
                employeeService.getMaleEmployeesWithF().forEach(System.out::println);
                break;
            default:
                System.out.println("Неверный режим");
        }
    }

    private void printUsage() {
        System.out.println("Использование: employee <режим> [параметры]");
        System.out.println("Режимы:");
        System.out.println("  2 - Добавить сотрудника: employee <полное имя> <дата рождения> <пол>");
        System.out.println("  3 - Распечатать всех сотрудников");
        System.out.println("  4 - Генерация случайных данных");
        System.out.println("  5 - Запросить мужчин-сотрудников с 'F'");
    }
}
