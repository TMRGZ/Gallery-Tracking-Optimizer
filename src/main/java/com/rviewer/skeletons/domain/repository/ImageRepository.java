package com.rviewer.skeletons.domain.repository;

import com.rviewer.skeletons.domain.model.Image;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ImageRepository {

    Flux<Image> findAll();

    Flux<Image> saveAll(Flux<Image> imageFlux);

    Mono<Void> deleteAll();
}
