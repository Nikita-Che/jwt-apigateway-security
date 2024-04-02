package org.example.identityservice.controller;

import org.example.identityservice.dto.AuthenticationRequestDto;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @PostMapping("/token")
    public String getToken(@RequestBody AuthenticationRequestDto authRequest) {
        return "HET";
    }

    @GetMapping("/validate")
    public String validateToken (@RequestParam("token") String token){
        return "Привет";
    }
}
