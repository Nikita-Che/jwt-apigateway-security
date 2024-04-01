package org.example.identityservice.model;


import org.springframework.security.core.GrantedAuthority;

public enum RoleNameEnum implements GrantedAuthority {
    ROLE_ADMIN,
    ROLE_USER,
    ROLE_USER_API,
    USER_API,
    ROLE_OPERATOR;

    @Override
    public String getAuthority() {
        return name();
    }
}