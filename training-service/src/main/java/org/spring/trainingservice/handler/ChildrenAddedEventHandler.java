package org.spring.trainingservice.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.spring.trainingservice.persistance.model.ChildrenCreatedEvent;
import org.springframework.kafka.annotation.KafkaHandler;
import org.springframework.stereotype.Component;

@Component
//@KafkaListener(topics = "children-created-events-topic")
public class ChildrenAddedEventHandler {
    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    @KafkaHandler
    public void handle(ChildrenCreatedEvent event) {
        LOGGER.info("Received product created event: {}" + event.getName());
        //вот тут надо добавить в базу данных тренировку ребенку
    }
}