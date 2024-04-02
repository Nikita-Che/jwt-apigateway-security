package org.example.identityservice.mapper;

import org.example.identityservice.dto.AuthenticationUserDto;
import org.example.identityservice.model.Role;
import org.example.identityservice.model.RoleNameEnum;
import org.example.identityservice.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface AuthenticationUserMapper {

    @Mapping(target = "roleNames", expression = "java(rolesToRoleNames(user.getRoles()))")
    AuthenticationUserDto userToAuthenticationUserDto(User user);

    default Set<RoleNameEnum> rolesToRoleNames(Set<Role> roles) {
        return roles.stream().map(Role::getName).collect(Collectors.toSet());
    }
}
