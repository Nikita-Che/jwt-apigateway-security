package org.spring.trainingservice.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("t")
public class TrainingController {

    @GetMapping
    public String hello () {
        return "hello from trainig!!!!!!!!!!!!!!!!!!";
    }
}
