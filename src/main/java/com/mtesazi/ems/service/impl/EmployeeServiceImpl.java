package com.mtesazi.ems.service.impl;

import com.mtesazi.ems.dto.EmployeeDto;
import com.mtesazi.ems.mapper.EmployeeMapper;
import com.mtesazi.ems.model.Employee;
import com.mtesazi.ems.repository.EmployeeRepository;
import com.mtesazi.ems.service.EmployeeService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class EmployeeServiceImpl  implements EmployeeService {
    private EmployeeRepository employeeRepository;
    @Override
    public EmployeeDto createEmployee(EmployeeDto employeeDto) {
        Employee employee = EmployeeMapper.mapToEmployee(employeeDto);
        Employee savedEmployee = employeeRepository.save(employee);
        return EmployeeMapper.mapToEmployeeDto(savedEmployee);
    }
}
