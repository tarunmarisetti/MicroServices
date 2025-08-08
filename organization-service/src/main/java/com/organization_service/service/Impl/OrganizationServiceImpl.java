package com.organization_service.service.Impl;

import com.organization_service.dto.OrganizationDto;
import com.organization_service.entity.Organization;
import com.organization_service.mapper.OrganizationMapper;
import com.organization_service.repository.OrganizationRepository;
import com.organization_service.service.OrganizationService;
import org.springframework.stereotype.Service;



@Service
public class OrganizationServiceImpl implements OrganizationService {
    private final OrganizationRepository organizationRepository;
    private final OrganizationMapper mapper;

    public OrganizationServiceImpl(OrganizationRepository organizationRepository, OrganizationMapper mapper) {
        this.organizationRepository = organizationRepository;
        this.mapper = mapper;
    }


    @Override
    public OrganizationDto saveOrganization(OrganizationDto organizationDto) {
        Organization organization=mapper.mapToEntity(organizationDto);
        Organization savedOrganization=organizationRepository.save(organization);
        return mapper.mapToDto(savedOrganization);
    }

    @Override
    public OrganizationDto getOrganizationByCode(String code) {
        Organization organization=organizationRepository.getOrganizationByOrganizationCode(code);
        return mapper.mapToDto(organization);
    }
}
