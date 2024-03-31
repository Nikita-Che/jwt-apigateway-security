package org.spring.childrenservice.controllers;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("children")
public class ChildrenController {
    @Value("${eureka.instance.instance-id}")
    private String port;

    @GetMapping("hello")
    public String helloController() {
        return "Hello from children from port: " + port;
    }
}
