package com.rviewer.skeletons.domain.repository;

import com.rviewer.skeletons.domain.model.Image;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

public interface ImageRepository {

    Flux<Image> findAll();

    Mono<Void> deleteAll();

    Mono<Image> findById(UUID id);

    Mono<Image> save(Image image);
}
