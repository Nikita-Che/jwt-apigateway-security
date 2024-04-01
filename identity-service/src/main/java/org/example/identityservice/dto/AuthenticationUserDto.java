package org.example.identityservice.dto;

import org.example.identityservice.model.RoleNameEnum;

import java.io.Serializable;
import java.util.Set;

public record AuthenticationUserDto(String email, Set<RoleNameEnum> roleNames) implements Serializable {
}