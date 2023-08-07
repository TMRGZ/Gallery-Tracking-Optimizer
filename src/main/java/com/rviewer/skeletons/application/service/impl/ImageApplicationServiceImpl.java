package com.rviewer.skeletons.application.service.impl;

import com.rviewer.skeletons.application.mapper.EventDtoMapper;
import com.rviewer.skeletons.application.mapper.ImageDtoMapper;
import com.rviewer.skeletons.application.service.ImageApplicationService;
import com.rviewer.skeletons.domain.service.event.EventService;
import com.rviewer.skeletons.domain.service.image.ImageService;
import generated.com.rviewer.skeletons.application.model.ImageInfoDto;
import generated.com.rviewer.skeletons.application.model.TrackEventBodyDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Service
public class ImageApplicationServiceImpl implements ImageApplicationService {

    @Autowired
    private ImageService imageService;

    @Autowired
    private ImageDtoMapper imageDtoMapper;

    @Autowired
    private EventService eventService;

    @Autowired
    private EventDtoMapper eventDtoMapper;

    @Override
    public Mono<ResponseEntity<Flux<ImageInfoDto>>> getImageList(String algorithm) {
        Flux<ImageInfoDto> imageInfoDtoFlux = imageService.getSortedImages(algorithm).map(imageDtoMapper::map);
        return Mono.just(ResponseEntity.ok(imageInfoDtoFlux));
    }

    @Override
    public Mono<ResponseEntity<Void>> postImageEvents(String imageId, Mono<TrackEventBodyDto> trackEventBodyDto) {
        return trackEventBodyDto.map(eventDtoMapper::map)
                .map(event -> event.toBuilder().imageId(UUID.fromString(imageId)).build())
                .flatMap(eventService::save)
                .then(Mono.just(ResponseEntity.noContent().build()));
    }
}
