package com.rviewer.skeletons.application.service;

import generated.com.rviewer.skeletons.application.model.ImageInfoDto;
import generated.com.rviewer.skeletons.application.model.TrackEventBodyDto;
import org.springframework.http.ResponseEntity;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ImageApplicationService {

    Mono<ResponseEntity<Flux<ImageInfoDto>>> getImageList();

    Mono<ResponseEntity<Void>> postImageEvents(String imageId, Mono<TrackEventBodyDto> trackEventBodyDto);

}
