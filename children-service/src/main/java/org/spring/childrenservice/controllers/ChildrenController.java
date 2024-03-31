package org.spring.childrenservice.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("c")
public class ChildrenController {

    @GetMapping
    public String helloController() {
        return "Hello from children";
    }
}
