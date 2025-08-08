package com.organization_service.mapper;

import com.organization_service.dto.OrganizationDto;
import com.organization_service.entity.Organization;
import org.springframework.stereotype.Component;

@Component
public class OrganizationMapper {
    public OrganizationDto mapToDto(Organization organization){
        return new OrganizationDto(
                organization.getId(),
                organization.getOrganizationName(),
                organization.getOrganizationDescription(),
                organization.getOrganizationCode(),
                organization.getCreatedDate()
        );
    }
    public Organization mapToEntity(OrganizationDto organization){
        return new Organization(
                organization.getId(),
                organization.getOrganizationName(),
                organization.getOrganizationDescription(),
                organization.getOrganizationCode(),
                organization.getCreatedDate()
        );
    }
}
