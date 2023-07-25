package com.rviewer.skeletons.domain.service.image;

import com.rviewer.skeletons.domain.model.Image;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

public interface ImageService {

    Flux<Image> getImages();

    Mono<Image> getImage(UUID id);

    Mono<Image> save(Image image);

    Flux<Image> saveImages(Flux<Image> imageFlux);

    Mono<Void> deleteAllImages();

}
