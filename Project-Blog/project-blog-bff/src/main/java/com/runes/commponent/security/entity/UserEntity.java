package com.runes.commponent.security.entity;


import com.runes.commponent.security.infrastructure.SecurityComponentConstants;
import jakarta.persistence.*;
import lombok.Data;


@Entity
@Table(schema = SecurityComponentConstants.SCHEMA_SECURITY,name = SecurityComponentConstants.TABLE_USER)
@Data
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(length = 36)
    private String id;

    @Column(length = 500)
    private String password;

    @Column(length = 64)
    private  String username;

    @Column
    private  boolean accountNonExpired;

    @Column
    private  boolean accountNonLocked;

    @Column
    private  boolean credentialsNonExpired;

    @Column
    private  boolean enabled;

}
