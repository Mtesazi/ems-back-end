package com.mtesazi.ems.repository;

import com.mtesazi.ems.model.Employee;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@DisplayName("Employee Repository Tests")
class EmployeeRepositoryTests {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private TestEntityManager entityManager;

    private Employee employee;

    @BeforeEach
    void setUp() {
        employee = new Employee();
        employee.setFirstName("John");
        employee.setLastName("Doe");
        employee.setEmailAddress("john@example.com");
        employee.setContactNumber("1234567890");
    }

    @Test
    @DisplayName("Should save employee successfully")
    void testSaveEmployee() {
        Employee savedEmployee = employeeRepository.save(employee);
        entityManager.flush();

        assertNotNull(savedEmployee);
        assertNotNull(savedEmployee.getId());
        assertEquals("John", savedEmployee.getFirstName());
        assertEquals("john@example.com", savedEmployee.getEmailAddress());
    }

    @Test
    @DisplayName("Should find employee by id successfully")
    void testFindEmployeeById() {
        Employee savedEmployee = employeeRepository.save(employee);
        entityManager.flush();
        entityManager.clear();

        Optional<Employee> foundEmployee = employeeRepository.findById(savedEmployee.getId());

        assertTrue(foundEmployee.isPresent());
        assertEquals("John", foundEmployee.get().getFirstName());
        assertEquals("john@example.com", foundEmployee.get().getEmailAddress());
    }

    @Test
    @DisplayName("Should return empty Optional when employee not found")
    void testFindEmployeeByIdNotFound() {
        Optional<Employee> foundEmployee = employeeRepository.findById(999L);

        assertFalse(foundEmployee.isPresent());
    }

    @Test
    @DisplayName("Should find all employees successfully")
    void testFindAllEmployees() {
        Employee employee2 = new Employee();
        employee2.setFirstName("Jane");
        employee2.setLastName("Smith");
        employee2.setEmailAddress("jane@example.com");
        employee2.setContactNumber("9876543210");

        employeeRepository.save(employee);
        employeeRepository.save(employee2);
        entityManager.flush();

        List<Employee> employees = employeeRepository.findAll();

        assertEquals(2, employees.size());
    }

    @Test
    @DisplayName("Should update employee successfully")
    void testUpdateEmployee() {
        Employee savedEmployee = employeeRepository.save(employee);
        entityManager.flush();

        savedEmployee.setFirstName("Updated");
        savedEmployee.setLastName("Employee");
        savedEmployee.setEmailAddress("updated@example.com");

        Employee updatedEmployee = employeeRepository.save(savedEmployee);
        entityManager.flush();

        assertEquals("Updated", updatedEmployee.getFirstName());
        assertEquals("updated@example.com", updatedEmployee.getEmailAddress());
    }

    @Test
    @DisplayName("Should delete employee successfully")
    void testDeleteEmployee() {
        Employee savedEmployee = employeeRepository.save(employee);
        entityManager.flush();
        Long employeeId = savedEmployee.getId();

        employeeRepository.deleteById(employeeId);
        entityManager.flush();

        Optional<Employee> foundEmployee = employeeRepository.findById(employeeId);
        assertFalse(foundEmployee.isPresent());
    }

    @Test
    @DisplayName("Should return empty list when no employees exist")
    void testFindAllEmployeesEmpty() {
        List<Employee> employees = employeeRepository.findAll();

        assertTrue(employees.isEmpty());
    }

    @Test
    @DisplayName("Should count all employees")
    void testCountEmployees() {
        Employee employee2 = new Employee();
        employee2.setFirstName("Jane");
        employee2.setLastName("Smith");
        employee2.setEmailAddress("jane@example.com");
        employee2.setContactNumber("9876543210");

        employeeRepository.save(employee);
        employeeRepository.save(employee2);
        entityManager.flush();

        long count = employeeRepository.count();

        assertEquals(2, count);
    }
}
