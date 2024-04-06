package org.spring.childrenservice.usecases;

import org.spring.childrenservice.persistance.model.Children;
import org.spring.childrenservice.persistance.model.ChildrenCreatedEvent;

public interface ChildrenKafkaService {
    String createLogMessage (String message);

    String createChildrenMessage(Children children);

}
