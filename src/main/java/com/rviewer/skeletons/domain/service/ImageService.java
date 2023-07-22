package com.rviewer.skeletons.domain.service;

import com.rviewer.skeletons.domain.model.Image;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ImageService {

    Flux<Image> getImages();

    Flux<Image> saveImages(Flux<Image> imageFlux);

    Mono<Void> deleteAllImages();

}
