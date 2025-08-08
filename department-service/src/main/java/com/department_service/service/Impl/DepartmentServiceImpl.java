package com.department_service.service.Impl;

import com.department_service.dto.DepartmentDto;
import com.department_service.entity.Department;
import com.department_service.mapper.DepartmentMapper;
import com.department_service.repository.DepartmentRepository;
import com.department_service.service.DepartmentService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentServiceImpl implements DepartmentService {
    private final DepartmentRepository departmentRepository;
    private final DepartmentMapper departmentMapper;

    public DepartmentServiceImpl(DepartmentRepository departmentRepository, DepartmentMapper departmentMapper) {
        this.departmentRepository = departmentRepository;
        this.departmentMapper = departmentMapper;
    }

    @Override
    public DepartmentDto createDepartment(DepartmentDto departmentDto) {
        Department department=departmentMapper.mapToEntity(departmentDto);
        Department savedDepartment=departmentRepository.save(department);
        return departmentMapper.mapToDto(savedDepartment);
    }

    @Override
    public DepartmentDto getDepartmentByCode(String code) {
        Department fetchedDepartment=departmentRepository.findByDepartmentCode(code);
        DepartmentDto departmentDto=departmentMapper.mapToDto(fetchedDepartment);
        return  departmentDto;
    }

    @Override
    public List<DepartmentDto> getAllDepartments() {
        return List.of();
    }

    @Override
    public DepartmentDto updateDepartment(Long id, DepartmentDto departmentDto) {
        return null;
    }

    @Override
    public void deleteDepartment(Long id) {

    }
}
