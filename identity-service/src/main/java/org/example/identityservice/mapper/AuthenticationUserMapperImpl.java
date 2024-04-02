package org.example.identityservice.mapper;

import org.example.identityservice.dto.AuthenticationUserDto;
import org.example.identityservice.model.RoleNameEnum;
import org.example.identityservice.model.User;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public class AuthenticationUserMapperImpl implements AuthenticationUserMapper {

    @Override
    public AuthenticationUserDto userToAuthenticationUserDto(User user) {
        if ( user == null ) {
            return null;
        }

        String email = null;

        email = user.getEmail();

        Set<RoleNameEnum> roleNames = rolesToRoleNames(user.getRoles());

        AuthenticationUserDto authenticationUserDto = new AuthenticationUserDto( email, roleNames );

        return authenticationUserDto;
    }
}
