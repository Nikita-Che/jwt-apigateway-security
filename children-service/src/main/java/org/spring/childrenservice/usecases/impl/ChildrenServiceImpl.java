package org.spring.childrenservice.usecases.impl;

import lombok.RequiredArgsConstructor;
import org.spring.childrenservice.persistance.model.Children;
import org.spring.childrenservice.persistance.repository.ChildrenRepository;
import org.spring.childrenservice.usecases.ChildrenService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ChildrenServiceImpl implements ChildrenService {

    private final ChildrenRepository childrenRepository;

    @Override
    public List<Children> findAll() {
        return childrenRepository.findAll();
    }

    @Override
    public Children getById(Long id) {
        Optional<Children> optionalChildren = childrenRepository.findById(id);
        return optionalChildren.orElse(null);
    }

    @Override
    public void save(Children children) {
        childrenRepository.save(children);
    }

    @Override
    public void delete(Children children) {
        childrenRepository.delete(children);
    }
}