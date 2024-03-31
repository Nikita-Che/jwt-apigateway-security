package org.spring.trainingservice.controllers;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("training")
public class TrainingController {

    @Value("${eureka.instance.instance-id}")
    private String port;

    @GetMapping("hello")
    public String helloController() {
        return "Hello from TRAINING from port: " + port;
    }
}
