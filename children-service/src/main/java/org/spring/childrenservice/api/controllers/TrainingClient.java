package org.spring.childrenservice.api.controllers;

import lombok.RequiredArgsConstructor;
import org.spring.childrenservice.persistance.dto.AuthRequest;
import org.spring.childrenservice.usecases.ChildrenService;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
@RequiredArgsConstructor
@RequestMapping("/children")
public class TrainingClient {

    private final ChildrenService childrenService;
    private final String trainingServiceUrl = "http://localhost:8765"; // URL микросервиса training через гейтвей
    private final String trainingServiceUrlFid = "http://localhost:8091"; // URL микросервиса training прямой
    private final String authTokenUrl = "http://localhost:8765/auth/token";
    private final RestTemplate restTemplate;

    @GetMapping("/training")
    public ResponseEntity<String> getTrainingList() {
        ResponseEntity<String> response = restTemplate.getForEntity(trainingServiceUrlFid + "/training/all", String.class);
        return ResponseEntity.ok(response.getBody());
    }

    @GetMapping("/training/{id}")
    public ResponseEntity<String> getTrainingById(@PathVariable Long id) {
        ResponseEntity<String> response = restTemplate.getForEntity(trainingServiceUrlFid + "/training/" + id, String.class);
        return ResponseEntity.ok(response.getBody());
    }

    //убери в метод  AuthRequest authRequest = new AuthRequest("Vasya", "root"); Потому что это костыль
    // да и вообще такой запрос это костыль
    @GetMapping("/trainigheader")
    public ResponseEntity<String> getTrainingListWithHeader() {
        AuthRequest authRequest = new AuthRequest("Vasya", "root");
        String jwtToken = getToken(authRequest);
        HttpHeaders headers = new HttpHeaders();
        headers.set(HttpHeaders.AUTHORIZATION, "Bearer " + jwtToken); // Добавляем JWT токен в заголовок Authorization

        HttpEntity<String> entity = new HttpEntity<>(headers);

        ResponseEntity<String> response = restTemplate.exchange(trainingServiceUrl + "/training/all", HttpMethod.GET, entity, String.class);
        return response;
    }

    @GetMapping("/trainigheader/{id}")
    public ResponseEntity<String> getTrainingByIdWithHeader(@PathVariable Long id) {
        AuthRequest authRequest = new AuthRequest("Vasya", "root");
        String jwtToken = getToken(authRequest);
        HttpHeaders headers = new HttpHeaders();
        headers.set(HttpHeaders.AUTHORIZATION, "Bearer " + jwtToken); // Добавляем JWT токен в заголовок Authorization

        HttpEntity<String> entity = new HttpEntity<>(headers);

        ResponseEntity<String> response = restTemplate.exchange(trainingServiceUrl + "/training/" + id, HttpMethod.GET, entity, String.class);
        return response;
    }

    @PostMapping("/token")
    public String getToken(@RequestBody AuthRequest authRequest) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<AuthRequest> requestEntity = new HttpEntity<>(authRequest, headers);

        ResponseEntity<String> responseEntity = restTemplate.postForEntity(authTokenUrl, requestEntity, String.class);
        String jwttoken = responseEntity.getBody();
        return jwttoken;
    }
}
