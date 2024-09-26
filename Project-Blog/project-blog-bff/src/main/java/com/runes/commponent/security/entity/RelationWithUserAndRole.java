package com.runes.commponent.security.entity;

import com.runes.commponent.security.infrastructure.SecurityComponentConstants;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(schema = SecurityComponentConstants.SCHEMA_SECURITY,name = SecurityComponentConstants.TABLE_RELATION_WITH_USER_AND_ROLE)
@Data
public class RelationWithUserAndRole {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(length = 36)
    private String id;

    @Column(length = 36)
    private String userId;

    @Column(length = 36)
    private String roleId;
}
