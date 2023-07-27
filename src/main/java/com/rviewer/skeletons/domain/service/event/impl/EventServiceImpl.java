package com.rviewer.skeletons.domain.service.event.impl;

import com.rviewer.skeletons.domain.exception.ImageNotFoundException;
import com.rviewer.skeletons.domain.model.Event;
import com.rviewer.skeletons.domain.model.Image;
import com.rviewer.skeletons.domain.model.ImageInfoEvents;
import com.rviewer.skeletons.domain.repository.EventRepository;
import com.rviewer.skeletons.domain.service.event.EventService;
import com.rviewer.skeletons.domain.service.image.ImageService;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;
import java.util.UUID;

@RequiredArgsConstructor
public class EventServiceImpl implements EventService {

    private final EventRepository eventRepository;

    private final ImageService imageService;

    @Override
    public Mono<Event> save(Event event) {
        UUID imageId = event.getImageId();

        Mono<Event> savedMono = eventRepository.save(event);
        return imageService.getImage(imageId).switchIfEmpty(
                        Mono.error(new ImageNotFoundException("Image with id = %s not found".formatted(imageId))))
                .zipWith(savedMono)
                .zipWith(eventRepository.countByImageIdAndEventType(imageId, event.getEventType()))
                .flatMap(tuple -> {
                    Event tupleEvent = tuple.getT1().getT2();
                    Image tupleImage = tuple.getT1().getT1();
                    long tupleCount = tuple.getT2();

                    return persistImageEvents(tupleImage, tupleEvent, tupleCount);
                })
                .flatMap(imageService::save)
                .then(savedMono);
    }

    private Mono<Image> persistImageEvents(Image image, Event event, long count) {
        ImageInfoEvents.ImageInfoEventsBuilder eventsBuilder = image.getEvents().toBuilder();

        switch (event.getEventType()) {
            case VIEW -> eventsBuilder.views(BigDecimal.valueOf(count));
            case CLICK -> eventsBuilder.clicks(BigDecimal.valueOf(count));
        }

        ImageInfoEvents updatedEvents = eventsBuilder.build();

        return Mono.just(image.toBuilder().events(updatedEvents).build());
    }

    @Override
    public Mono<Event> getAllEventsFromAnImage(UUID imageId) {
        return eventRepository.findByImageId(imageId);
    }
}
