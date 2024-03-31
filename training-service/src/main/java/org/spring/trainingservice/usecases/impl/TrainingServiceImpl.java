package org.spring.trainingservice.usecases.impl;

import lombok.RequiredArgsConstructor;
import org.spring.trainingservice.persistance.model.Training;
import org.spring.trainingservice.persistance.repository.TrainingRepository;
import org.spring.trainingservice.usecases.TrainingService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TrainingServiceImpl implements TrainingService {

    private final TrainingRepository trainingRepository;

    @Override
    public List<Training> findAll() {
        return trainingRepository.findAll();
    }

    @Override
    public Training getById(Long id) {
        Optional<Training> optionalChildren = trainingRepository.findById(id);
        return optionalChildren.orElse(null);
    }

    @Override
    public Training save(Training children) {
        trainingRepository.save(children);
        return children;
    }

    @Override
    public void delete(Training children) {
        trainingRepository.delete(children);
    }
}