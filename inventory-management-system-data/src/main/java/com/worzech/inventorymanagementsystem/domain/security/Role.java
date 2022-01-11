package com.worzech.inventorymanagementsystem.domain.security;

import com.google.common.collect.Sets;
import lombok.Getter;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Set;
import java.util.stream.Collectors;

import static com.worzech.inventorymanagementsystem.domain.security.Permission.*;

public enum Role {
    COORDINATOR(Sets.newHashSet(DISTRICT_READ, PROJECT_READ, DEMAND_READ, DEMAND_WRITE, POPCMATERIAL_READ)),
    ADMIN(Sets.newHashSet(
            DISTRICT_READ, DISTRICT_WRITE, DISTRICT_UPDATE, DISTRICT_DELETE,
            PROJECT_READ, PROJECT_WRITE, PROJECT_UPDATE, PROJECT_DELETE,
            DEMAND_READ, DEMAND_WRITE, DEMAND_UPDATE, DEMAND_DELETE,
            POPCMATERIAL_READ));


    @Getter
    private final Set<Permission> permissions;

    Role(Set<Permission> permissions) {
        this.permissions = permissions;
    }

    public Set<SimpleGrantedAuthority> getGrantedAuthorities() {
        Set<SimpleGrantedAuthority> permissions = getPermissions().stream()
                .map(permission -> new SimpleGrantedAuthority(permission.getPermission()))
                .collect(Collectors.toSet());
        permissions.add(new SimpleGrantedAuthority("ROLE_" + this.name()));
        return permissions;
    }
}

