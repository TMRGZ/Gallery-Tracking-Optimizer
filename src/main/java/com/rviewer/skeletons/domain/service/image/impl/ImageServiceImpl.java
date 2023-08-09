package com.rviewer.skeletons.domain.service.image.impl;

import com.rviewer.skeletons.domain.factory.SorterFactory;
import com.rviewer.skeletons.domain.model.Image;
import com.rviewer.skeletons.domain.repository.ImageRepository;
import com.rviewer.skeletons.domain.service.image.ImageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
public class ImageServiceImpl implements ImageService {

    private final ImageRepository imageRepository;

    private final SorterFactory sorterFactory;

    @Override
    public Flux<Image> getSortedImages(String algorithm) {
        log.info("Sorting images by algorithm {}", algorithm);
        return sorterFactory.getSorter(algorithm)
                .getSortedImages();
    }

    @Override
    public Mono<Image> getImage(UUID id) {
        log.info("Getting image with id {}", id);
        return imageRepository.findById(id);
    }

    @Override
    public Mono<Image> save(Image image) {
        log.info("Persisting image {}", image);
        return imageRepository.save(image);
    }

    @Override
    public Flux<Image> saveImages(Flux<Image> imageFlux) {
        log.info("Saving images in batch...");
        return imageFlux.flatMap(this::save);
    }

    @Override
    public Mono<Void> deleteAllImages() {
        log.info("Deleting all images...");
        return imageRepository.deleteAll();
    }
}
