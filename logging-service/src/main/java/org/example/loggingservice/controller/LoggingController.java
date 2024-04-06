package org.example.loggingservice.controller;

import lombok.RequiredArgsConstructor;
import org.example.loggingservice.service.LoggingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@KafkaListener(topics = "children-logging-events-topic")
@RequiredArgsConstructor
public class LoggingController {
    private final LoggingService loggingService;
    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    @KafkaHandler
    public void handler(String message) {

        loggingService.save(message);
        LOGGER.info("Saved: {}" + message);
    }

}
