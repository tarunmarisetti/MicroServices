package com.organization_service.repository;

import com.organization_service.dto.OrganizationDto;
import com.organization_service.entity.Organization;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrganizationRepository extends JpaRepository<Organization,Long> {
    Organization getOrganizationByOrganizationCode(String code);
}
