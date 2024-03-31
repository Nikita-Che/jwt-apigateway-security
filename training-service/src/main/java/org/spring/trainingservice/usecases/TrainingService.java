package org.spring.trainingservice.usecases;

import org.spring.trainingservice.persistance.model.Training;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface TrainingService {

    List<Training> findAll();

    Training getById(Long id);

    Training save(Training children);

    void delete (Training children);
}