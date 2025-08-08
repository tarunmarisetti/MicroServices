package com.organization_service.dto;

import lombok.*;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class OrganizationDto {
    private Long id;
    private String organizationName;
    private String organizationDescription;
    private String organizationCode;
    private LocalDateTime createdDate;
}
