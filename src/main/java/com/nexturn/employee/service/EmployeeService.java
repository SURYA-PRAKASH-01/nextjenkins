package com.nexturn.employee.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.nexturn.employee.Employee;

@Service
public class EmployeeService {

    private final List<Employee> employees = new ArrayList<>();
    private int nextId = 1;

    // Add employee
    public Employee addEmployee(Employee employee) {
        employee.setId(nextId++);
        employees.add(employee);
        return employee;
    }

    // Find all employees
    public List<Employee> findAll() {
        return employees;
    }

    // Find employee by ID
    public Employee findById(int id) {
        return employees.stream()
                .filter(e -> e.getId() == id)
                .findFirst()
                .orElse(null);
    }

    // Update employee
    public Employee updateEmployee(int id, Employee updatedEmployee) {
        Employee employee = findById(id);

        if (employee != null) {
            employee.setName(updatedEmployee.getName());
            employee.setSalary(updatedEmployee.getSalary());
        }

        return employee;
    }

    // Delete employee
    public boolean deleteEmployee(int id) {
        return employees.removeIf(e -> e.getId() == id);
    }
}