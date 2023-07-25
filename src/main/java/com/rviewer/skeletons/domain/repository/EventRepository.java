package com.rviewer.skeletons.domain.repository;

import com.rviewer.skeletons.domain.model.Event;
import reactor.core.publisher.Mono;

import java.util.UUID;

public interface EventRepository {

    Mono<Event> save(Event event);

    Mono<Event> findByImageId(UUID imageId);

    Mono<Long> countByImageIdAndEventType(UUID imageId, String eventType);

}
