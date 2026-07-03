package com.mtesazi.ems.service.impl;

import com.mtesazi.ems.dto.EmployeeDto;
import com.mtesazi.ems.exception.ResourceNotFoundException;
import com.mtesazi.ems.model.Employee;
import com.mtesazi.ems.repository.EmployeeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("Employee Service Tests")
class EmployeeServiceImplTests {

    @Mock
    private EmployeeRepository employeeRepository;

    @InjectMocks
    private EmployeeServiceImpl employeeService;

    private Employee employee;
    private EmployeeDto employeeDto;

    @BeforeEach
    void setUp() {
        employee = new Employee();
        employee.setId(1L);
        employee.setFirstName("John");
        employee.setLastName("Doe");
        employee.setEmailAddress("john@example.com");
        employee.setContactNumber("1234567890");

        employeeDto = new EmployeeDto();
        employeeDto.setId(1L);
        employeeDto.setFirstName("John");
        employeeDto.setLastName("Doe");
        employeeDto.setEmailAddress("john@example.com");
        employeeDto.setContactNumber("1234567890");
    }

    @Test
    @DisplayName("Should create employee successfully")
    void testCreateEmployee() {
        when(employeeRepository.save(any(Employee.class))).thenReturn(employee);

        EmployeeDto result = employeeService.createEmployee(employeeDto);

        assertNotNull(result);
        assertEquals("John", result.getFirstName());
        assertEquals("john@example.com", result.getEmailAddress());
        verify(employeeRepository, times(1)).save(any(Employee.class));
    }

    @Test
    @DisplayName("Should get employee by id successfully")
    void testGetEmployeeById() {
        when(employeeRepository.findById(1L)).thenReturn(Optional.of(employee));

        EmployeeDto result = employeeService.getEmployeeById(1L);

        assertNotNull(result);
        assertEquals("John", result.getFirstName());
        assertEquals(1L, result.getId());
    }

    @Test
    @DisplayName("Should throw ResourceNotFoundException when employee not found by id")
    void testGetEmployeeByIdNotFound() {
        when(employeeRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> {
            employeeService.getEmployeeById(1L);
        });

        verify(employeeRepository, times(1)).findById(1L);
    }

    @Test
    @DisplayName("Should get all employees successfully")
    void testGetAllEmployees() {
        Employee employee2 = new Employee();
        employee2.setId(2L);
        employee2.setFirstName("Jane");
        employee2.setLastName("Smith");
        employee2.setEmailAddress("jane@example.com");
        employee2.setContactNumber("9876543210");

        when(employeeRepository.findAll()).thenReturn(Arrays.asList(employee, employee2));

        List<EmployeeDto> result = employeeService.getAllEmployees();

        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals("John", result.get(0).getFirstName());
        assertEquals("Jane", result.get(1).getFirstName());
    }

    @Test
    @DisplayName("Should return empty list when no employees exist")
    void testGetAllEmployeesEmpty() {
        when(employeeRepository.findAll()).thenReturn(Arrays.asList());

        List<EmployeeDto> result = employeeService.getAllEmployees();

        assertNotNull(result);
        assertTrue(result.isEmpty());
    }

    @Test
    @DisplayName("Should update employee successfully")
    void testUpdateEmployee() {
        EmployeeDto updateDto = new EmployeeDto();
        updateDto.setFirstName("Updated");
        updateDto.setLastName("Name");
        updateDto.setEmailAddress("updated@example.com");
        updateDto.setContactNumber("5555555555");

        Employee updatedEmployee = new Employee();
        updatedEmployee.setId(1L);
        updatedEmployee.setFirstName("Updated");
        updatedEmployee.setLastName("Name");
        updatedEmployee.setEmailAddress("updated@example.com");
        updatedEmployee.setContactNumber("5555555555");

        when(employeeRepository.findById(1L)).thenReturn(Optional.of(employee));
        when(employeeRepository.save(any(Employee.class))).thenReturn(updatedEmployee);

        EmployeeDto result = employeeService.updateEmployee(1L, updateDto);

        assertNotNull(result);
        assertEquals("Updated", result.getFirstName());
        assertEquals("updated@example.com", result.getEmailAddress());
        verify(employeeRepository, times(1)).findById(1L);
        verify(employeeRepository, times(1)).save(any(Employee.class));
    }

    @Test
    @DisplayName("Should throw ResourceNotFoundException when updating non-existent employee")
    void testUpdateEmployeeNotFound() {
        when(employeeRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> {
            employeeService.updateEmployee(1L, employeeDto);
        });

        verify(employeeRepository, times(1)).findById(1L);
    }

    @Test
    @DisplayName("Should delete employee successfully")
    void testDeleteEmployee() {
        when(employeeRepository.findById(1L)).thenReturn(Optional.of(employee));

        assertDoesNotThrow(() -> {
            employeeService.deleteEmployee(1L);
        });

        verify(employeeRepository, times(1)).findById(1L);
        verify(employeeRepository, times(1)).deleteById(1L);
    }

    @Test
    @DisplayName("Should throw ResourceNotFoundException when deleting non-existent employee")
    void testDeleteEmployeeNotFound() {
        when(employeeRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> {
            employeeService.deleteEmployee(1L);
        });

        verify(employeeRepository, times(1)).findById(1L);
        verify(employeeRepository, never()).deleteById(1L);
    }
}
