package org.spring.childrenservice.api.controllers;

import lombok.RequiredArgsConstructor;
import org.spring.childrenservice.usecases.ChildrenService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequiredArgsConstructor
@RequestMapping("/children")
public class TrainingClient {

    private final ChildrenService childrenService;
    private final String trainingServiceUrl = "http://localhost:8765"; // URL микросервиса training
    private final RestTemplate restTemplate;

    @GetMapping("/training")
    public ResponseEntity<String> getTrainingList() {
        ResponseEntity<String> response = restTemplate.getForEntity(trainingServiceUrl + "/training/all", String.class);
        return ResponseEntity.ok(response.getBody());
    }

    @GetMapping("/training/{id}")
    public ResponseEntity<String> getTrainingById(@PathVariable Long id) {
        ResponseEntity<String> response = restTemplate.getForEntity(trainingServiceUrl + "/training/" + id, String.class);
        return ResponseEntity.ok(response.getBody());
    }
}
