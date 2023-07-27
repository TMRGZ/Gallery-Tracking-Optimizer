package com.rviewer.skeletons.infrastructure.persistence.repository.impl;

import com.rviewer.skeletons.domain.model.Event;
import com.rviewer.skeletons.domain.repository.EventRepository;
import com.rviewer.skeletons.infrastructure.mapper.EventDaoMapper;
import com.rviewer.skeletons.infrastructure.persistence.repository.mongo.EventMongoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.util.Optional;
import java.util.UUID;

@Component
public class EventRepositoryImpl implements EventRepository {

    @Autowired
    private EventMongoRepository eventMongoRepository;

    @Autowired
    private EventDaoMapper eventDaoMapper;

    @Override
    public Mono<Event> save(Event event) {
        return eventMongoRepository.save(eventDaoMapper.map(generateId(event)))
                .map(eventDaoMapper::map);
    }

    private Event generateId(Event event) {
        Optional<UUID> id = Optional.ofNullable(event.getId());

        if (id.isEmpty()) {
            event.setId(UUID.randomUUID());
        }

        return event;
    }

    @Override
    public Mono<Event> findByImageId(UUID imageId) {
        return eventMongoRepository.findByImageId(imageId)
                .map(eventDaoMapper::map);
    }

    @Override
    public Mono<Long> countByImageIdAndEventType(UUID imageId, String eventType) {
        return eventMongoRepository.countByImageIdAndEventType(imageId, eventType);
    }
}
