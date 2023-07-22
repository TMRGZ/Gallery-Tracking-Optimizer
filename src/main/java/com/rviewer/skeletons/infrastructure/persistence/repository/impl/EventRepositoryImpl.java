package com.rviewer.skeletons.infrastructure.persistence.repository.impl;

import com.rviewer.skeletons.domain.model.Event;
import com.rviewer.skeletons.domain.repository.EventRepository;
import com.rviewer.skeletons.infrastructure.mapper.EventDaoMapper;
import com.rviewer.skeletons.infrastructure.persistence.repository.mongo.EventMongoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Component
public class EventRepositoryImpl implements EventRepository {

    @Autowired
    private EventMongoRepository eventMongoRepository;

    @Autowired
    private EventDaoMapper eventDaoMapper;

    @Override
    public Mono<Event> save(Event event) {
        return eventMongoRepository.save(eventDaoMapper.map(event))
                .map(eventDaoMapper::map);
    }

    @Override
    public Flux<Event> findByImageId(UUID imageId) {
        return eventMongoRepository.findByImageId(imageId)
                .map(eventDaoMapper::map);
    }
}
