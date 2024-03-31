package org.spring.trainingservice.api.controllers;

import lombok.RequiredArgsConstructor;
import org.spring.trainingservice.persistance.model.Training;
import org.spring.trainingservice.usecases.TrainingService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/training")
public class TrainingController {

    private final TrainingService trainingService;

    @Value("${eureka.instance.instance-id}")
    private String port;

    @GetMapping("/hello")
    public String helloController() {
        return "Hello from TRAINING from port: " + port;
    }

    @GetMapping("/all")
    public List<Training> getAll() {
        return trainingService.findAll();
    }

    @GetMapping("{id}")
    public ResponseEntity<Training> getById(@PathVariable Long id) {
        Training training = trainingService.getById(id);
        if (training != null) {
            return ResponseEntity.ok(training);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public Training save(@RequestBody Training training) {
        return trainingService.save(training);
    }

    @DeleteMapping("{id}")
    public void deleteById(@PathVariable Long id) {
        Training training = trainingService.getById(id);
        trainingService.delete(training);
    }
}
