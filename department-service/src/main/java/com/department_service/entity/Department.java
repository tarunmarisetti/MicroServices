package com.department_service.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Table(name = "departments")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Department {
    @Id@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String departmentName;
    @Column
    private String departmentDescription;
    @Column
    private String departmentCode;
}
