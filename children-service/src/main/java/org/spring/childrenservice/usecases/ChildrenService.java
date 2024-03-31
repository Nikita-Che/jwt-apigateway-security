package org.spring.childrenservice.usecases;

import org.spring.childrenservice.persistance.model.Children;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ChildrenService {

    List<Children> findAll();

    Children getById(Long id);

    void save(Children children);

    void delete (Children children);
}