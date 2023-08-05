package com.rviewer.skeletons.domain.service.event;

import com.rviewer.skeletons.domain.model.Event;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

public interface EventService {

    Mono<Event> save(Event event);

    Flux<Event> getAllEventsFromAnImage(UUID imageId);

}
