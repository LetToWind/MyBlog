package com.runes.commponent.security.entity;


import com.runes.commponent.security.infrastructure.SecurityComponentConstants;
import jakarta.persistence.*;
import lombok.Data;


@Entity
@Table(schema = SecurityComponentConstants.SCHEMA_SECURITY,name = SecurityComponentConstants.TABLE_ROLE)
@Data
public class RoleEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(length = 36)
    private String id;

    @Column(length = 64)
    private String role;

    @Column(length = 255)
    private String roleDetails;
}
