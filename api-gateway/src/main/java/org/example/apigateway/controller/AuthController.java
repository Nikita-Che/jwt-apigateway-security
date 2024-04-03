package org.example.apigateway.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequiredArgsConstructor
public class AuthController {
    private final String authServiceUrl = "http://localhost:8765/identity-service/login"; // URL микросервиса training
    private final RestTemplate restTemplate = new RestTemplate();

    public String getJwt() {
        String token = "";
        ResponseEntity<Map<String, String>> mapJwt = getJwtFromAuthService();


        // вот тут надо проверить есть ли токен у человека.
        Map<String, String> responseBody = mapJwt.getBody();
        if (responseBody != null && responseBody.containsKey("token")) {
            System.out.println(token);
            token = responseBody.get("token");
        } else {
            System.out.println("Токен не найден в ответе");
        }
        return token;
    }

    public ResponseEntity<Map<String, String>> getJwtFromAuthService() {
        // разбить на части и захардкодить
        try {
            Map<String, String> requestMap = new HashMap<>();
            requestMap.put("email", "n@n.ru");
            requestMap.put("password", "root");

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            HttpEntity<Map<String, String>> requestEntity = new HttpEntity<>(requestMap, headers);

            ResponseEntity<String> responseEntity = restTemplate.exchange(
                    "http://localhost:8765/identity-service/login",
                    HttpMethod.POST,
                    requestEntity,
                    String.class
            );
            Map<String, String> responseBody = new HashMap<>();
            responseBody.put("token", responseEntity.getBody());

            return ResponseEntity.status(responseEntity.getStatusCode()).body(responseBody);
        } catch (ResourceAccessException e) {
            e.printStackTrace(); // или другие действия
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
}