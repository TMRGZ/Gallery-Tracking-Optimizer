package com.rviewer.skeletons.domain.service.event.impl;

import com.rviewer.skeletons.domain.model.Event;
import com.rviewer.skeletons.domain.repository.EventRepository;
import com.rviewer.skeletons.domain.service.event.EventService;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

@RequiredArgsConstructor
public class EventServiceImpl implements EventService {

    private final EventRepository eventRepository;

    @Override
    public Mono<Event> save(Event event) {
        return eventRepository.save(event);
    }

    @Override
    public Flux<Event> getAllEventsFromAnImage(UUID imageId) {
        return eventRepository.findByImageId(imageId);
    }
}
