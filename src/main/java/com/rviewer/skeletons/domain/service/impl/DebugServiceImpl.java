package com.rviewer.skeletons.domain.service.impl;

import com.rviewer.skeletons.domain.model.Image;
import com.rviewer.skeletons.domain.repository.ImageRepository;
import com.rviewer.skeletons.infrastructure.service.DatasetService;
import com.rviewer.skeletons.domain.service.DebugService;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
public class DebugServiceImpl implements DebugService {

    private final ImageRepository imageRepository;

    private final DatasetService datasetService;

    @Override
    public Flux<Image> importData() {
        Flux<Image> imageFlux = datasetService.retrieveData();
        return imageRepository.saveAll(imageFlux);
    }

    @Override
    public Mono<Void> removeImportedData() {
        return imageRepository.deleteAll();
    }
}
