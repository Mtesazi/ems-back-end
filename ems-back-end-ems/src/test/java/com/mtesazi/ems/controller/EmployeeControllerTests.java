package com.mtesazi.ems.controller;

import com.mtesazi.ems.dto.EmployeeDto;
import com.mtesazi.ems.service.EmployeeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("Employee Controller Tests")
class EmployeeControllerTests {

    @Mock
    private EmployeeService employeeService;

    @InjectMocks
    private EmployeeController employeeController;

    private EmployeeDto employeeDto;

    @BeforeEach
    void setUp() {
        employeeDto = new EmployeeDto();
        employeeDto.setId(1L);
        employeeDto.setFirstName("John");
        employeeDto.setLastName("Doe");
        employeeDto.setEmailAddress("john@example.com");
        employeeDto.setContactNumber("1234567890");
    }

    @Test
    @DisplayName("Should create employee and return 201 CREATED")
    void testCreateEmployee() {
        when(employeeService.createEmployee(any(EmployeeDto.class))).thenReturn(employeeDto);

        ResponseEntity<EmployeeDto> response = employeeController.createEmployee(employeeDto);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("John", response.getBody().getFirstName());
        verify(employeeService, times(1)).createEmployee(any(EmployeeDto.class));
    }

    @Test
    @DisplayName("Should get employee by id and return 200 OK")
    void testGetEmployeeById() {
        when(employeeService.getEmployeeById(1L)).thenReturn(employeeDto);

        ResponseEntity<EmployeeDto> response = employeeController.getEmployeeById(1L);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(1L, response.getBody().getId());
        verify(employeeService, times(1)).getEmployeeById(1L);
    }

    @Test
    @DisplayName("Should get all employees and return 200 OK")
    void testGetAllEmployees() {
        EmployeeDto employeeDto2 = new EmployeeDto();
        employeeDto2.setId(2L);
        employeeDto2.setFirstName("Jane");
        employeeDto2.setLastName("Smith");
        employeeDto2.setEmailAddress("jane@example.com");
        employeeDto2.setContactNumber("9876543210");

        when(employeeService.getAllEmployees()).thenReturn(Arrays.asList(employeeDto, employeeDto2));

        ResponseEntity<List<EmployeeDto>> response = employeeController.getAllEmployees();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(2, response.getBody().size());
        verify(employeeService, times(1)).getAllEmployees();
    }

    @Test
    @DisplayName("Should return empty list when no employees exist")
    void testGetAllEmployeesEmpty() {
        when(employeeService.getAllEmployees()).thenReturn(Arrays.asList());

        ResponseEntity<List<EmployeeDto>> response = employeeController.getAllEmployees();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertTrue(response.getBody().isEmpty());
    }

    @Test
    @DisplayName("Should update employee and return 200 OK")
    void testUpdateEmployee() {
        EmployeeDto updatedDto = new EmployeeDto();
        updatedDto.setId(1L);
        updatedDto.setFirstName("Updated");
        updatedDto.setLastName("Name");
        updatedDto.setEmailAddress("updated@example.com");
        updatedDto.setContactNumber("5555555555");

        when(employeeService.updateEmployee(1L, updatedDto)).thenReturn(updatedDto);

        ResponseEntity<EmployeeDto> response = employeeController.updateEmployee(1L, updatedDto);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("Updated", response.getBody().getFirstName());
        verify(employeeService, times(1)).updateEmployee(1L, updatedDto);
    }

    @Test
    @DisplayName("Should delete employee and return 200 OK")
    void testDeleteEmployee() {
        doNothing().when(employeeService).deleteEmployee(1L);

        ResponseEntity<String> response = employeeController.deleteEmployee(1L);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Employee deleted successfully", response.getBody());
        verify(employeeService, times(1)).deleteEmployee(1L);
    }
}
