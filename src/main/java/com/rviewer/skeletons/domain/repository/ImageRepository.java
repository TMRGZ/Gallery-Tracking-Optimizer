package com.rviewer.skeletons.domain.repository;

import com.rviewer.skeletons.domain.model.Image;
import reactor.core.publisher.Flux;

public interface ImageRepository {

    Flux<Image> findAll();

}
