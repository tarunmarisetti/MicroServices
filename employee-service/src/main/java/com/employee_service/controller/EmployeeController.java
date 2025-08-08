package com.employee_service.controller;

import com.employee_service.dto.ApiResponseDto;
import com.employee_service.dto.EmployeeDto;
import com.employee_service.service.impl.EmployeeServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/employees")
public class EmployeeController {
    private final EmployeeServiceImpl employeeService;

    public EmployeeController(EmployeeServiceImpl employeeService) {
        this.employeeService = employeeService;
    }

    @PostMapping
    public ResponseEntity<EmployeeDto> createDepartment(@RequestBody EmployeeDto departmentDto){
        EmployeeDto savedDepartment=employeeService.createEmployee(departmentDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedDepartment);
    }
    @GetMapping("{id}")
    public ResponseEntity<ApiResponseDto> getDepartmentByCode(@PathVariable("id") Long id){
        ApiResponseDto fetchedRecord=employeeService.getEmployeeById(id);
        return new ResponseEntity<>(fetchedRecord,HttpStatus.OK);
    }
}
