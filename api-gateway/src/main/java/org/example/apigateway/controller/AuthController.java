package org.example.apigateway.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@RestController
@RequiredArgsConstructor
public class AuthController {
    private final String authServiceUrl = "http://localhost:8765/identity-service/login"; // URL микросервиса training
    private final RestTemplate restTemplate = new RestTemplate();

    public String getJwt() {
        String token = "";
        //получаем мапу сходив в микросервис identity
        ResponseEntity<Map<String, String>> mapJwt = getJwtFromAuthService();
        // вот тут надо проверить есть ли токен у человека.

        //возвращаем из метода просто строку с токеном
        // путем преобразования  ResponseEntity<Map<String, String>> в Map<String, String>
        // а потом просто возвращаем строку token

        return token;
    }

    public ResponseEntity<Map<String, String>> getJwtFromAuthService() {
        //в этом методе надо сходить в identity
        // передать туда логи пароль
        // и получить ResponseEntity<Map<String, String>> с ответом
        //
        // и  вернуть в метод  getJwt

//            Map<String, String> requestMap = new HashMap<>();
//            requestMap.put("email", "n@n.ru");
//            requestMap.put("password", "root");

        ResponseEntity <Map<String, String>> entity = (ResponseEntity<Map<String, String>>) ResponseEntity.ok();
//            return ResponseEntity.status(responseEntity.getStatusCode()).body(responseBody);
        return entity;
    }
}