package org.example.identityservice.service;


import org.example.identityservice.dto.AuthenticationRequestDto;
import org.example.identityservice.dto.AuthenticationUserDto;

public interface AuthenticationService {
    AuthenticationUserDto findByEmailAndPassword(AuthenticationRequestDto requestDto);
}