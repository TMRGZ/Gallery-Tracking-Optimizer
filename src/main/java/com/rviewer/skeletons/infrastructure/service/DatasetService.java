package com.rviewer.skeletons.infrastructure.service;

import com.rviewer.skeletons.domain.model.Image;
import reactor.core.publisher.Flux;

public interface DatasetService {
    Flux<Image> retrieveData();
}
