package com.runes.commponent.security.service;


import com.runes.commponent.security.entity.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;


@Builder
@AllArgsConstructor
public class RunesUserDetails implements UserDetails {

    private final UserEntity userEntity;
    private final List<String> roleList;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roleList.stream().map(role ->
                new SimpleGrantedAuthority("ROLE_".concat(role))
        ).toList();
    }

    @Override
    public String getPassword() {
        return this.userEntity.getPassword();
    }

    @Override
    public String getUsername() {
        return this.userEntity.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return this.userEntity.isAccountNonExpired();
    }

    @Override
    public boolean isAccountNonLocked() {
        return this.userEntity.isAccountNonLocked();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return this.userEntity.isCredentialsNonExpired();
    }

    @Override
    public boolean isEnabled() {
        return this.userEntity.isEnabled();
    }
}
