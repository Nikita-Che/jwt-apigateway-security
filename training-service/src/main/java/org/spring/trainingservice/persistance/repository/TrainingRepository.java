package org.spring.trainingservice.persistance.repository;

import org.spring.trainingservice.persistance.model.Training;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TrainingRepository extends JpaRepository<Training, Long> {
}