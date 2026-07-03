package com.mtesazi.ems.mapper;

import com.mtesazi.ems.dto.EmployeeDto;
import com.mtesazi.ems.model.Employee;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Employee Mapper Tests")
class EmployeeMapperTests {

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
    @DisplayName("Should map Employee to EmployeeDto successfully")
    void testMapToEmployeeDto() {
        EmployeeDto result = EmployeeMapper.mapToEmployeeDto(employee);

        assertNotNull(result);
        assertEquals(1L, result.getId());
        assertEquals("John", result.getFirstName());
        assertEquals("Doe", result.getLastName());
        assertEquals("john@example.com", result.getEmailAddress());
        assertEquals("1234567890", result.getContactNumber());
    }

    @Test
    @DisplayName("Should map EmployeeDto to Employee successfully")
    void testMapToEmployee() {
        Employee result = EmployeeMapper.mapToEmployee(employeeDto);

        assertNotNull(result);
        assertEquals("John", result.getFirstName());
        assertEquals("Doe", result.getLastName());
        assertEquals("john@example.com", result.getEmailAddress());
        assertEquals("1234567890", result.getContactNumber());
    }

    @Test
    @DisplayName("Should handle null values in Employee to EmployeeDto mapping")
    void testMapToEmployeeDtoWithNulls() {
        Employee nullEmployee = new Employee();
        nullEmployee.setId(null);
        nullEmployee.setFirstName(null);
        nullEmployee.setLastName(null);
        nullEmployee.setEmailAddress(null);
        nullEmployee.setContactNumber(null);

        EmployeeDto result = EmployeeMapper.mapToEmployeeDto(nullEmployee);

        assertNotNull(result);
        assertNull(result.getId());
        assertNull(result.getFirstName());
        assertNull(result.getLastName());
        assertNull(result.getEmailAddress());
        assertNull(result.getContactNumber());
    }

    @Test
    @DisplayName("Should handle null values in EmployeeDto to Employee mapping")
    void testMapToEmployeeWithNulls() {
        EmployeeDto nullDto = new EmployeeDto();
        nullDto.setId(null);
        nullDto.setFirstName(null);
        nullDto.setLastName(null);
        nullDto.setEmailAddress(null);
        nullDto.setContactNumber(null);

        Employee result = EmployeeMapper.mapToEmployee(nullDto);

        assertNotNull(result);
        assertNull(result.getFirstName());
        assertNull(result.getLastName());
        assertNull(result.getEmailAddress());
        assertNull(result.getContactNumber());
    }

    @Test
    @DisplayName("Should preserve all data during bidirectional mapping")
    void testBidirectionalMapping() {
        EmployeeDto dtoFromEmployee = EmployeeMapper.mapToEmployeeDto(employee);
        Employee employeeFromDto = EmployeeMapper.mapToEmployee(dtoFromEmployee);
        EmployeeDto finalDto = EmployeeMapper.mapToEmployeeDto(employeeFromDto);

        assertEquals(dtoFromEmployee.getFirstName(), finalDto.getFirstName());
        assertEquals(dtoFromEmployee.getLastName(), finalDto.getLastName());
        assertEquals(dtoFromEmployee.getEmailAddress(), finalDto.getEmailAddress());
        assertEquals(dtoFromEmployee.getContactNumber(), finalDto.getContactNumber());
    }
}
