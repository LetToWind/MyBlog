package com.runes.commponent.security.repository;


import com.runes.commponent.security.entity.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<RoleEntity,String> {
}
