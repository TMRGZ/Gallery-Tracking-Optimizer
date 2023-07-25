package com.rviewer.skeletons.domain.service.event;

import com.rviewer.skeletons.domain.model.Event;
import reactor.core.publisher.Mono;

import java.util.UUID;

public interface EventService {

    Mono<Event> save(Event event);

    Mono<Event> getAllEventsFromAnImage(UUID imageId);

}
