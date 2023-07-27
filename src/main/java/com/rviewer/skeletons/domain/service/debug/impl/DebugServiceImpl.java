package com.rviewer.skeletons.domain.service.debug.impl;

import com.rviewer.skeletons.domain.model.Image;
import com.rviewer.skeletons.domain.service.debug.DebugService;
import com.rviewer.skeletons.domain.service.image.ImageService;
import com.rviewer.skeletons.infrastructure.service.DatasetService;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
public class DebugServiceImpl implements DebugService {

    private final ImageService imageService;

    private final DatasetService datasetService;

    @Override
    public Flux<Image> importData() {
        Flux<Image> imageFlux = datasetService.retrieveData();
        return imageService.saveImages(imageFlux);
    }

    @Override
    public Mono<Void> removeImportedData() {
        return imageService.deleteAllImages();
    }
}
