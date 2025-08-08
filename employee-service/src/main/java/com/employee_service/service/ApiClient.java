package com.employee_service.service;

import com.employee_service.dto.DepartmentDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "department-service")
public interface ApiClient {
    @GetMapping("departments/{departmentCode}")
    DepartmentDto getDepartmentByCode(@PathVariable("departmentCode") String code);
}
