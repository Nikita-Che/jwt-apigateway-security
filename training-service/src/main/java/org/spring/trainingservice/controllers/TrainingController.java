package org.spring.trainingservice.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("training")
public class TrainingController {

    @GetMapping("hello")
    public String hello () {
        return "hello from trainig!!!!!!!!!!!!!!!!!!";
    }
}
