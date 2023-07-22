package com.rviewer.skeletons.domain.service.image.impl;

import com.rviewer.skeletons.domain.model.Image;
import com.rviewer.skeletons.domain.repository.ImageRepository;
import com.rviewer.skeletons.domain.service.image.ImageService;
import com.rviewer.skeletons.domain.sorter.factory.SorterFactory;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
public class ImageServiceImpl implements ImageService {

    private final ImageRepository imageRepository;

    private final SorterFactory sorterFactory;

    @Override
    public Flux<Image> getImages() {
        return imageRepository.findAll()
                .sort(sorterFactory.getSorter(""));
    }

    @Override
    public Flux<Image> saveImages(Flux<Image> imageFlux) {
        return imageRepository.saveAll(imageFlux);
    }

    @Override
    public Mono<Void> deleteAllImages() {
        return imageRepository.deleteAll();
    }
}
