package org.spring.childrenservice.usecases.impl;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.spring.childrenservice.persistance.model.Children;
import org.spring.childrenservice.persistance.model.ChildrenCreatedEvent;
import org.spring.childrenservice.usecases.ChildrenKafkaService;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@Service
@RequiredArgsConstructor
public class ChildrenKafkaServiceImpl implements ChildrenKafkaService {

    private final KafkaTemplate<String, ChildrenCreatedEvent> kafkaTemplateChildren;
    private final KafkaTemplate<String, String> kafkaLoggingTemplate;
    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    @Override
    public String createLogMessage(String message) {
        String uuid = UUID.randomUUID().toString();
        CompletableFuture<SendResult<String, String>> future = kafkaLoggingTemplate.send(
                "children-logging-events-topic", uuid, message);
        future.whenComplete((result, exception) -> {
            if (exception != null) {
                LOGGER.error("Fail to sent message: {}", exception.getMessage());
            } else {
                LOGGER.info("Message sent successfully: {}", result.getRecordMetadata());
            }
        });

        LOGGER.info("Getting all", message);
        return uuid;
    }

    @Override
    public String createChildrenMessage(Children children) {
        String childrenId = String.valueOf(children.getId());

        ChildrenCreatedEvent childrenCreatedEvent = new ChildrenCreatedEvent(children.getId(), children.getName());

        CompletableFuture<SendResult<String, ChildrenCreatedEvent>> future = kafkaTemplateChildren.send(
                "children-created-events-topic", childrenId, childrenCreatedEvent);
        future.whenComplete((result, exception) -> {
            if (exception != null) {
                LOGGER.error("Fail to sent message: {}", exception.getMessage());
            } else {
                LOGGER.info("Message sent successfully: {}", result.getRecordMetadata());
            }
        });

        LOGGER.info("Returning product id {}", childrenId);
        return childrenId;
    }
}
