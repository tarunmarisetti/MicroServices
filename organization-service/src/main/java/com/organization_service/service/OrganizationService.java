package com.organization_service.service;

import com.organization_service.dto.OrganizationDto;
import com.organization_service.exceptions.SQLConstraintViolationException;

import java.util.List;

public interface OrganizationService {
    OrganizationDto saveOrganization(OrganizationDto organizationDto)throws SQLConstraintViolationException;
    OrganizationDto getOrganizationByCode(String code);
    List<OrganizationDto> getAllOrganizations();
    OrganizationDto updateOrganization(Long id, OrganizationDto organizationDto) throws SQLConstraintViolationException;
    void deleteOrganizationByCode(String code);
}
