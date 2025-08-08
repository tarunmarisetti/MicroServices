package com.organization_service.service.Impl;

import com.organization_service.dto.OrganizationDto;
import com.organization_service.entity.Organization;
import com.organization_service.exceptions.ResourceNotFoundException;
import com.organization_service.exceptions.SQLConstraintViolationException;
import com.organization_service.mapper.OrganizationMapper;
import com.organization_service.repository.OrganizationRepository;
import com.organization_service.service.OrganizationService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;


@Service
public class OrganizationServiceImpl implements OrganizationService {
    private final OrganizationRepository organizationRepository;
    private final ModelMapper modelMapper;

    public OrganizationServiceImpl(OrganizationRepository organizationRepository, ModelMapper modelMapper) {
        this.organizationRepository = organizationRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public OrganizationDto saveOrganization(OrganizationDto organizationDto) throws SQLConstraintViolationException {
        if(organizationRepository.existsByOrganizationCode(organizationDto.getOrganizationCode())){
            throw new SQLConstraintViolationException(organizationDto.getOrganizationCode());
        }
        Organization organization=new Organization();
        organization.setOrganizationCode(organizationDto.getOrganizationCode());
        organization.setOrganizationName(organizationDto.getOrganizationName());
        organization.setOrganizationDescription(organizationDto.getOrganizationDescription());
        Organization savedOrganization=organizationRepository.save(organization);
        return modelMapper.map(savedOrganization, OrganizationDto.class);
    }

    @Override
    public OrganizationDto getOrganizationByCode(String code) {
        Organization organization=organizationRepository.findByOrganizationCode(code)
                .orElseThrow(()-> new ResourceNotFoundException("department","department-code",code));
        return modelMapper.map(organization, OrganizationDto.class);
    }

    @Override
    public List<OrganizationDto> getAllOrganizations() {
        return organizationRepository.findAll().stream()
                .map(department -> modelMapper.map(department, OrganizationDto.class)).toList();
    }

    @Override
    public OrganizationDto updateOrganization(Long id, OrganizationDto organizationDto) throws SQLConstraintViolationException {
        Organization organization=organizationRepository.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("organization","id",id.toString()));
        if(Objects.equals(organization.getOrganizationCode(), organizationDto.getOrganizationCode())){
            throw new SQLConstraintViolationException(organizationDto.getOrganizationCode());
        }
        if(organizationDto.getOrganizationCode()!=null){
            organization.setOrganizationCode(organizationDto.getOrganizationCode());
        }
        if(organizationDto.getOrganizationName()!=null){
            organization.setOrganizationName(organizationDto.getOrganizationName());
        }
        if(organizationDto.getOrganizationDescription()!=null){
            organization.setOrganizationDescription(organizationDto.getOrganizationDescription());
        }
        Organization updatedDepartment=organizationRepository.save(organization);
        return modelMapper.map(updatedDepartment, OrganizationDto.class);
    }

    @Override
    public void deleteOrganizationByCode(String code) {
        Organization organization=organizationRepository.findByOrganizationCode(code)
                .orElseThrow(()->new ResourceNotFoundException("organization","organization-code",code));
        organizationRepository.delete(organization);


    }
}
