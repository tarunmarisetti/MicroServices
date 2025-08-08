package com.employee_service.service.impl;

import com.employee_service.dto.ApiResponseDto;
import com.employee_service.dto.DepartmentDto;
import com.employee_service.dto.EmployeeDto;
import com.employee_service.dto.OrganizationDto;
import com.employee_service.entity.Employee;
import com.employee_service.repository.EmployeeRepository;
import com.employee_service.service.EmployeeService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private static final Logger LOGGER= LoggerFactory.getLogger(EmployeeServiceImpl.class);
    private final EmployeeRepository employeeRepository;
    private final ModelMapper modelMapper;
//    private final RestTemplate restTemplate;
    private final WebClient webClient;
//    private ApiClient apiClient;

    @Override
    public EmployeeDto createEmployee(EmployeeDto employeeDto) {
        Employee employee=modelMapper.map(employeeDto,Employee.class);
        Employee savedEmployee=employeeRepository.save(employee);
        return modelMapper.map(savedEmployee,EmployeeDto.class);
    }

//    @CircuitBreaker(name = "${spring.application.name}", fallbackMethod = "getDefaultDepartment")
    @Retry(name = "${spring.application.name}",fallbackMethod = "getDefaultDepartment")
    @Override
    public ApiResponseDto getEmployeeById(Long id) {
        LOGGER.info("inside getEmployeeById method");
        Employee employee=employeeRepository.findById(id).orElseThrow(()->new RuntimeException("no record found"));
//        ResponseEntity<DepartmentDto> responseEntity=restTemplate.getForEntity("http://localhost:8080/departments/"+employee.getDepartmentCode(), DepartmentDto.class);
//        DepartmentDto departmentDto=responseEntity.getBody();
        DepartmentDto departmentDto=webClient.get()
                .uri("http://localhost:8080/departments/"+employee.getDepartmentCode())
                .retrieve()
                .bodyToMono(DepartmentDto.class)
                .block();
//        DepartmentDto departmentDto=apiClient.getDepartmentByCode(employee.getDepartmentCode());

        OrganizationDto organizationDto=webClient.get()
                .uri("http://localhost:8083/organizations/"+employee.getOrganizationCode())
                .retrieve()
                .bodyToMono(OrganizationDto.class)
                .block();
        employee.setDepartmentCode(departmentDto.getDepartmentCode());
        employee.setOrganizationCode(organizationDto.getOrganizationCode());
        return new ApiResponseDto(modelMapper.map(employee,EmployeeDto.class),departmentDto,organizationDto);
    }

    public ApiResponseDto getDefaultDepartment(Long id,Exception exception) {
        LOGGER.info("inside getDefaultDepartment method");
        Employee employee=employeeRepository.findById(id).orElseThrow(()->new RuntimeException("no record found"));
        DepartmentDto departmentDto=new DepartmentDto();
        departmentDto.setDepartmentName("R&D Department");
        departmentDto.setDepartmentCode("RD001");
        departmentDto.setDepartmentDescription("Research and development department");

        OrganizationDto organizationDto=new OrganizationDto();
        organizationDto.setOrganizationName("defaultOrganization");
        organizationDto.setOrganizationDescription("defaultDescription");
        organizationDto.setOrganizationCode("defaultCode");
        organizationDto.setCreatedDate(LocalDateTime.now());

        employee.setDepartmentCode(departmentDto.getDepartmentCode());
        return new ApiResponseDto(modelMapper.map(employee,EmployeeDto.class),departmentDto,organizationDto);

    }
}
