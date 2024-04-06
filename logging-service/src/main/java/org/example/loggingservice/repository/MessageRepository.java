package org.example.loggingservice.repository;

import org.example.loggingservice.model.LoggingMessage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MessageRepository extends JpaRepository<LoggingMessage, Long> {
}
