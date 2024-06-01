package com.mtesazi.ems.mapper;

import com.mtesazi.ems.dto.EmployeeDto;
import com.mtesazi.ems.model.Employee;

public class EmployeeMapper {
    public static EmployeeDto mapToEmployeeDto(Employee employee) {
        return new EmployeeDto(
                employee.getId(),
                employee.getFirstName(),
                employee.getLastName(),
                employee.getContactNumber(),
                employee.getEmailAddress()
        );
    }

    public static Employee mapToEmployee(EmployeeDto employeeDto) {
        return new Employee(
                employeeDto.getId(),
                employeeDto.getFirstName(),
                employeeDto.getLastName(),
                employeeDto.getEmailAddress(),
                employeeDto.getContactNumber()
        );
    }
}
