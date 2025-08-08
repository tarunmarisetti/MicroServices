package com.department_service.service;

import com.department_service.dto.DepartmentDto;
import com.department_service.exceptions.SQLConstraintViolationException;

import java.util.List;

public interface DepartmentService {
    DepartmentDto createDepartment(DepartmentDto departmentDto) throws SQLConstraintViolationException;
    DepartmentDto getDepartmentByCode(String code);
    List<DepartmentDto> getAllDepartments();
    DepartmentDto updateDepartment(Long id, DepartmentDto departmentDto) throws SQLConstraintViolationException;
    void deleteDepartmentByCode(String code);
}
