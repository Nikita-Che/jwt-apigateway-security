package org.spring.childrenservice.api.controllers;

import lombok.RequiredArgsConstructor;
import org.spring.childrenservice.persistance.model.Children;
import org.spring.childrenservice.persistance.repository.ChildrenRepository;
import org.spring.childrenservice.usecases.ChildrenService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/children")
public class ChildrenController {

    private final ChildrenService childrenService;

    @Value("${eureka.instance.instance-id}")
    private String port;
    private final ChildrenRepository childrenRepository;

    @GetMapping("/hello")
    public String helloController() {
        return "Hello from children from port: " + port;
    }

    @GetMapping("/all")
    public List<Children> getAll() {
        return childrenService.findAll();
    }

    @GetMapping("{id}")
    public ResponseEntity<Children> getById(@PathVariable Long id) {
        Children child = childrenService.getById(id);
        if (child != null) {
            return ResponseEntity.ok(child);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public Children save(@RequestBody Children children) {
        return childrenRepository.save(children);
    }

    @DeleteMapping("{id}")
    public void deleteById(@PathVariable Long id) {
        Children children = childrenService.getById(id);
        childrenService.delete(children);
    }
}
