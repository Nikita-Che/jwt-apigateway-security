package org.example.loggingservice.service;

import lombok.RequiredArgsConstructor;
import org.example.loggingservice.model.LoggingMessage;
import org.example.loggingservice.repository.MessageRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
@RequiredArgsConstructor
public class LoggingService {
    private final MessageRepository messageRepository;

    public void save(String message) {
        LocalDateTime currentTime = LocalDateTime.now();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedTime = currentTime.format(formatter);

        String messageToAdd = formattedTime + " - " + message;

        LoggingMessage loggingMessage = new LoggingMessage();
        loggingMessage.setMessage(messageToAdd);
        messageRepository.save(loggingMessage);
    }
}
