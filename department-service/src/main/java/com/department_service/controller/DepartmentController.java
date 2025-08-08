package com.department_service.controller;

import com.department_service.dto.DepartmentDto;
import com.department_service.exceptions.SQLConstraintViolationException;
import com.department_service.service.Impl.DepartmentServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/departments")
public class DepartmentController {

    private final DepartmentServiceImpl departmentService;

    public DepartmentController(DepartmentServiceImpl departmentService) {
        this.departmentService = departmentService;
    }

    @PostMapping
    public ResponseEntity<DepartmentDto> createDepartment(@RequestBody DepartmentDto departmentDto) throws SQLConstraintViolationException {
        DepartmentDto savedDepartment=departmentService.createDepartment(departmentDto);
        return new ResponseEntity<>(savedDepartment,HttpStatus.CREATED);
    }
    @GetMapping("{departmentCode}")
    public ResponseEntity<DepartmentDto> getDepartmentByCode(@PathVariable("departmentCode") String code){
        DepartmentDto fetchedDepartment=departmentService.getDepartmentByCode(code);
        return new ResponseEntity<>(fetchedDepartment,HttpStatus.OK);
    }

    @GetMapping("")
    public ResponseEntity<List<DepartmentDto>> getAllDepartment(){
        List<DepartmentDto> departmentDtos=departmentService.getAllDepartments();
        return new ResponseEntity<>(departmentDtos,HttpStatus.OK);
    }

    @PatchMapping("{id}")
    public ResponseEntity<DepartmentDto> updateDepartment(@PathVariable("id") Long id, @RequestBody DepartmentDto departmentDto) throws SQLConstraintViolationException {
        DepartmentDto updateDepartment=departmentService.updateDepartment(id,departmentDto);
        return new ResponseEntity<>(updateDepartment,HttpStatus.OK);
    }

    @DeleteMapping("{departmentCode}")
    public ResponseEntity<String> deleteDepartmentByCode(@PathVariable("departmentCode") String code){
        departmentService.deleteDepartmentByCode(code);
        return new ResponseEntity<>("record deleted",HttpStatus.OK);
    }

}
