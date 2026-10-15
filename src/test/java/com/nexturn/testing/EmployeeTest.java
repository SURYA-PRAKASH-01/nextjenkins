
package com.nexturn.testing;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.nexturn.employee.Employee;
import com.nexturn.employee.service.EmployeeService;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class EmployeeTest {

    private EmployeeService employeeService;

    @BeforeEach
    void setUp() {
        employeeService = new EmployeeService();
    }

    private Employee createEmployee(String name, double salary) {
        return new Employee(0, name, salary);
    }

    // 1. Test adding an employee
    @Test
    void testAddEmployee() {
        Employee result = employeeService.addEmployee(
                createEmployee("Rahul", 35000));

        assertNotNull(result);
        assertEquals(1, result.getId());
        assertEquals("Rahul", result.getName());
        assertEquals(35000.0, result.getSalary());
        assertEquals(1, employeeService.findAll().size());
    }

    // 2. Test adding multiple employees
    @Test
    void testAddMultipleEmployees() {
        employeeService.addEmployee(createEmployee("Rahul", 35000));
        employeeService.addEmployee(createEmployee("Priya", 40000));

        assertEquals(2, employeeService.findAll().size());
    }

    // 3. Test retrieving all employees
    @Test
    void testFindAllEmployees() {
        employeeService.addEmployee(createEmployee("Rahul", 35000));
        employeeService.addEmployee(createEmployee("Priya", 45000));

        List<Employee> employees = employeeService.findAll();

        assertNotNull(employees);
        assertEquals(2, employees.size());
    }

    // 4. Test retrieving an employee by ID
    @Test
    void testFindById() {
        Employee added = employeeService.addEmployee(
                createEmployee("Rahul", 35000));

        Employee result = employeeService.findById(added.getId());

        assertNotNull(result);
        assertEquals("Rahul", result.getName());
        assertEquals(35000.0, result.getSalary());
    }

    // 5. Test searching for an invalid ID
    @Test
    void testFindByInvalidId() {
        Employee result = employeeService.findById(999);

        assertNull(result);
    }

    // 6. Test updating an employee
    @Test
    void testUpdateEmployee() {
        Employee added = employeeService.addEmployee(
                createEmployee("Rahul", 35000));

        Employee updatedData =
                createEmployee("Rahul Kumar", 40000);

        Employee result = employeeService.updateEmployee(
                added.getId(), updatedData);

        assertNotNull(result);
        assertEquals("Rahul Kumar", result.getName());
        assertEquals(40000.0, result.getSalary());
    }

    // 7. Test updating a nonexistent employee
    @Test
    void testUpdateNonExistingEmployee() {
        Employee result = employeeService.updateEmployee(
                999, createEmployee("Unknown", 30000));

        assertNull(result);
    }

    // 8. Test deleting an employee
    @Test
    void testDeleteEmployee() {
        Employee added = employeeService.addEmployee(
                createEmployee("Rahul", 35000));

        boolean result =
                employeeService.deleteEmployee(added.getId());

        assertTrue(result);
        assertNull(employeeService.findById(added.getId()));
        assertEquals(0, employeeService.findAll().size());
    }

    // 9. Test deleting a nonexistent employee
    @Test
    void testDeleteNonExistingEmployee() {
        boolean result = employeeService.deleteEmployee(999);

        assertFalse(result);
    }
}
