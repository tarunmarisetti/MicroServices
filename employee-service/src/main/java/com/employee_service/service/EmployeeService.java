package com.employee_service.service;

import com.employee_service.dto.ApiResponseDto;
import com.employee_service.dto.EmployeeDto;

public interface EmployeeService {
    EmployeeDto createEmployee(EmployeeDto employeeDto);
    ApiResponseDto getEmployeeById(Long id);
}
