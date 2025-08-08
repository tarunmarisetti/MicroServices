package com.organization_service.controller;

import com.organization_service.dto.OrganizationDto;
import com.organization_service.entity.Organization;
import com.organization_service.exceptions.SQLConstraintViolationException;
import com.organization_service.service.Impl.OrganizationServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/organizations")
public class OrganizationController {
    private final OrganizationServiceImpl organizationService;

    public OrganizationController(OrganizationServiceImpl organizationService) {
        this.organizationService = organizationService;
    }

    @PostMapping
    public ResponseEntity<OrganizationDto> saveOrganization(@RequestBody OrganizationDto organizationDto) throws SQLConstraintViolationException {
        OrganizationDto savedOrganization=organizationService.saveOrganization(organizationDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedOrganization);
    }
    @GetMapping("/{code}")
    public ResponseEntity<OrganizationDto> getOrganizationByCode(@PathVariable("code") String code){
        OrganizationDto savedOrganization=organizationService.getOrganizationByCode(code);
        return ResponseEntity.status(HttpStatus.OK).body(savedOrganization);
    }
    @GetMapping("")
    public ResponseEntity<List<OrganizationDto>> getAllOrganizations(){
        List<OrganizationDto> organizationDtos=organizationService.getAllOrganizations();
        return new ResponseEntity<>(organizationDtos,HttpStatus.OK);
    }

    @PatchMapping("{id}")
    public ResponseEntity<OrganizationDto> updateOrganization(@PathVariable("id") Long id, @RequestBody OrganizationDto organizationDto) throws SQLConstraintViolationException {
        OrganizationDto updateDepartment=organizationService.updateOrganization(id,organizationDto);
        return new ResponseEntity<>(updateDepartment,HttpStatus.OK);
    }

    @DeleteMapping("{departmentCode}")
    public ResponseEntity<String> deleteOrganizationByCode(@PathVariable("departmentCode") String code){
        organizationService.deleteOrganizationByCode(code);
        return new ResponseEntity<>("record deleted",HttpStatus.OK);
    }


}
