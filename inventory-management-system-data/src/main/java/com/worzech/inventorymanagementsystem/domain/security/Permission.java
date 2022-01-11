package com.worzech.inventorymanagementsystem.domain.security;

import lombok.Getter;

public enum Permission {
    DISTRICT_READ("district:read"),
    DISTRICT_WRITE("district:write"),
    DISTRICT_UPDATE("district:update"),
    DISTRICT_DELETE("district:delete"),

    PROJECT_READ("project:read"),
    PROJECT_WRITE("project:write"),
    PROJECT_UPDATE("project:update"),
    PROJECT_DELETE("project:delete"),

    DEMAND_READ("demand:read"),
    DEMAND_WRITE("demand:write"),
    DEMAND_UPDATE("demand:update"),
    DEMAND_DELETE("demand:delete"),

    POPCMATERIAL_READ("popcMaterial:read");

    @Getter
    private final String permission;

    Permission(String permission) {
        this.permission = permission;
    }

}
