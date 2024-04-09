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
//        String jwtToken = getToken(authRequest);
        String jwtToken = "eyJhbGciOiJSUzI1NiIsInR5cCIgOiAiSldUIiwia2lkIiA6ICJOQVRyaXJfRUJNVG5mUkFfbzhJbXE4OEFrZEh1YWkwcTdlLXAzTndQY0FNIn0.eyJleHAiOjE3MTI2NzQxMzEsImlhdCI6MTcxMjY3MjMzMSwiYXV0aF90aW1lIjoxNzEyNjcxMTI3LCJqdGkiOiIyM2NhOTQyZS1iYjZlLTQ2ZWUtOTRkOS1mYTgyODAxODlmYTgiLCJpc3MiOiJodHRwOi8vbG9jYWxob3N0OjgwODAvcmVhbG1zL3VzZXItcmVhbG0iLCJhdWQiOiJhY2NvdW50Iiwic3ViIjoiZDgyNTE0YzYtODliNi00OTQyLTliOWMtNTdkOWMxNjAyMTlhIiwidHlwIjoiQmVhcmVyIiwiYXpwIjoiand0LWFwaWdhdGV3YXktc2VjdXJpdHkiLCJzZXNzaW9uX3N0YXRlIjoiMGY4YThjOWUtZWJkMy00NjBkLWJhYzgtYTQyZjg1NjYyNTJhIiwiYWNyIjoiMCIsImFsbG93ZWQtb3JpZ2lucyI6WyIvKiJdLCJyZWFsbV9hY2Nlc3MiOnsicm9sZXMiOlsib2ZmbGluZV9hY2Nlc3MiLCJkZWZhdWx0LXJvbGVzLXVzZXItcmVhbG0iLCJhZG1pbiIsInVtYV9hdXRob3JpemF0aW9uIl19LCJyZXNvdXJjZV9hY2Nlc3MiOnsiYWNjb3VudCI6eyJyb2xlcyI6WyJtYW5hZ2UtYWNjb3VudCIsIm1hbmFnZS1hY2NvdW50LWxpbmtzIiwidmlldy1wcm9maWxlIl19fSwic2NvcGUiOiJvcGVuaWQgZW1haWwgcHJvZmlsZSIsInNpZCI6IjBmOGE4YzllLWViZDMtNDYwZC1iYWM4LWE0MmY4NTY2MjUyYSIsImVtYWlsX3ZlcmlmaWVkIjpmYWxzZSwicHJlZmVycmVkX3VzZXJuYW1lIjoibmlrIiwiZ2l2ZW5fbmFtZSI6IiIsImZhbWlseV9uYW1lIjoiIiwiZW1haWwiOiJuQG4ucnUifQ.QFs93Tz_7jR5uSSOVhFvdGGspyvDROccCLQ3Gx_sEJF-3vVwM1qix2JhObqsI7B4REHa_14m6fA5HybfIlz4ffHDrqBLZNQoTEDQ9dZ2TVJbvgqjzrhu0lUxp2RVBx4WFwKOghxJDQOFdIbWU98VkqouAaWKJL92fpDEgOqw8oN0tIpEtBmILa0RLwFWLPRmGxIWbNc8UEVflKS-acdRzvWlAaB6n9imWRr0CjGz2NHDmYEoHYnpv6CEK9jTI_sjigLYMR5ium6wSyGFeKKRlo2IULC--dHKc-ugWbmB0puzLDZVuau35EX9HeP64as_BpUTScFU-bV3wqZGl5gFfQ";
        HttpHeaders headers = new HttpHeaders();
        headers.set(HttpHeaders.AUTHORIZATION, "Bearer " + jwtToken); // Добавляем JWT токен в заголовок Authorization

        HttpEntity<String> entity = new HttpEntity<>(headers);

        ResponseEntity<String> response = restTemplate.exchange(trainingServiceUrl + "/training/all", HttpMethod.GET, entity, String.class);
        return response;
    }

    @GetMapping("/trainigheader/{id}")
    public ResponseEntity<String> getTrainingByIdWithHeader(@PathVariable Long id) {
        AuthRequest authRequest = new AuthRequest("Vasya", "root");
//        String jwtToken = getToken(authRequest);
        String jwtToken = "eyJhbGciOiJSUzI1NiIsInR5cCIgOiAiSldUIiwia2lkIiA6ICJOQVRyaXJfRUJNVG5mUkFfbzhJbXE4OEFrZEh1YWkwcTdlLXAzTndQY0FNIn0.eyJleHAiOjE3MTI2NzQxMzEsImlhdCI6MTcxMjY3MjMzMSwiYXV0aF90aW1lIjoxNzEyNjcxMTI3LCJqdGkiOiIyM2NhOTQyZS1iYjZlLTQ2ZWUtOTRkOS1mYTgyODAxODlmYTgiLCJpc3MiOiJodHRwOi8vbG9jYWxob3N0OjgwODAvcmVhbG1zL3VzZXItcmVhbG0iLCJhdWQiOiJhY2NvdW50Iiwic3ViIjoiZDgyNTE0YzYtODliNi00OTQyLTliOWMtNTdkOWMxNjAyMTlhIiwidHlwIjoiQmVhcmVyIiwiYXpwIjoiand0LWFwaWdhdGV3YXktc2VjdXJpdHkiLCJzZXNzaW9uX3N0YXRlIjoiMGY4YThjOWUtZWJkMy00NjBkLWJhYzgtYTQyZjg1NjYyNTJhIiwiYWNyIjoiMCIsImFsbG93ZWQtb3JpZ2lucyI6WyIvKiJdLCJyZWFsbV9hY2Nlc3MiOnsicm9sZXMiOlsib2ZmbGluZV9hY2Nlc3MiLCJkZWZhdWx0LXJvbGVzLXVzZXItcmVhbG0iLCJhZG1pbiIsInVtYV9hdXRob3JpemF0aW9uIl19LCJyZXNvdXJjZV9hY2Nlc3MiOnsiYWNjb3VudCI6eyJyb2xlcyI6WyJtYW5hZ2UtYWNjb3VudCIsIm1hbmFnZS1hY2NvdW50LWxpbmtzIiwidmlldy1wcm9maWxlIl19fSwic2NvcGUiOiJvcGVuaWQgZW1haWwgcHJvZmlsZSIsInNpZCI6IjBmOGE4YzllLWViZDMtNDYwZC1iYWM4LWE0MmY4NTY2MjUyYSIsImVtYWlsX3ZlcmlmaWVkIjpmYWxzZSwicHJlZmVycmVkX3VzZXJuYW1lIjoibmlrIiwiZ2l2ZW5fbmFtZSI6IiIsImZhbWlseV9uYW1lIjoiIiwiZW1haWwiOiJuQG4ucnUifQ.QFs93Tz_7jR5uSSOVhFvdGGspyvDROccCLQ3Gx_sEJF-3vVwM1qix2JhObqsI7B4REHa_14m6fA5HybfIlz4ffHDrqBLZNQoTEDQ9dZ2TVJbvgqjzrhu0lUxp2RVBx4WFwKOghxJDQOFdIbWU98VkqouAaWKJL92fpDEgOqw8oN0tIpEtBmILa0RLwFWLPRmGxIWbNc8UEVflKS-acdRzvWlAaB6n9imWRr0CjGz2NHDmYEoHYnpv6CEK9jTI_sjigLYMR5ium6wSyGFeKKRlo2IULC--dHKc-ugWbmB0puzLDZVuau35EX9HeP64as_BpUTScFU-bV3wqZGl5gFfQ";
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
