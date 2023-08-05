package com.rviewer.skeletons.domain.service.event.impl;

import com.rviewer.skeletons.domain.exception.ImageNotFoundException;
import com.rviewer.skeletons.domain.model.Event;
import com.rviewer.skeletons.domain.model.Image;
import com.rviewer.skeletons.domain.model.ImageInfoEvents;
import com.rviewer.skeletons.domain.repository.EventRepository;
import com.rviewer.skeletons.domain.service.event.EventService;
import com.rviewer.skeletons.domain.service.image.ImageService;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
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
        Mono<Image> foundImage = imageService.getImage(imageId);
        Mono<Event> savedEvent = eventRepository.save(event);
        Mono<Long> countedEvents = eventRepository.countByImageIdAndEventType(imageId, event.getEventType());

        return foundImage.switchIfEmpty(Mono.defer(() ->
                        Mono.error(new ImageNotFoundException(imageId))))
                .then(savedEvent)
                .flatMap(e -> Mono.zip(Mono.just(e), foundImage, countedEvents))
                .flatMap(tuple -> {
                    Event tupleEvent = tuple.getT1();
                    Image tupleImage = tuple.getT2();
                    long tupleCount = tuple.getT3();

                    return persistImageEvents(tupleImage, tupleEvent, tupleCount);
                })
                .then(savedEvent);
    }

    private Mono<Image> persistImageEvents(Image image, Event event, long count) {
        ImageInfoEvents.ImageInfoEventsBuilder eventsBuilder = image.getEvents().toBuilder();

        switch (event.getEventType()) {
            case VIEW -> eventsBuilder.views(BigDecimal.valueOf(count));
            case CLICK -> eventsBuilder.clicks(BigDecimal.valueOf(count));
        }

        ImageInfoEvents updatedEvents = eventsBuilder.build();
        Image imageToSave = image.toBuilder().events(updatedEvents).build();

        return imageService.save(imageToSave);
    }

    @Override
    public Flux<Event> getAllEventsFromAnImage(UUID imageId) {
        return eventRepository.findByImageId(imageId);
    }
}
