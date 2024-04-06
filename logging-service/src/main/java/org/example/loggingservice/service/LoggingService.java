package org.example.loggingservice.service;

import lombok.RequiredArgsConstructor;
import org.example.loggingservice.model.LoggingMessage;
import org.example.loggingservice.repository.MessageRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LoggingService {
    private final MessageRepository messageRepository;

    public void save(String message) {
        LoggingMessage loggingMessage = new LoggingMessage();
        loggingMessage.setMessage(message);
        messageRepository.save(loggingMessage);
    }
}
