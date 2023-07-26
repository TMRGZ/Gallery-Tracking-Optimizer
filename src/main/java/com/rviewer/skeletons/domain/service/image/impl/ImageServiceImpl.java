package com.rviewer.skeletons.domain.service.image.impl;

import com.rviewer.skeletons.domain.model.Image;
import com.rviewer.skeletons.domain.repository.ImageRepository;
import com.rviewer.skeletons.domain.service.image.ImageService;
import com.rviewer.skeletons.domain.factory.SorterFactory;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.util.function.Tuple2;

import java.math.BigDecimal;
import java.util.UUID;

@RequiredArgsConstructor
public class ImageServiceImpl implements ImageService {

    private final ImageRepository imageRepository;

    private final SorterFactory sorterFactory;

    @Override
    public Flux<Image> getImages(String algorithm) {
        return sorterFactory.getSorter(algorithm)
                .getSortedImages();
    }

    @Override
    public Mono<Image> getImage(UUID id) {
        return imageRepository.findById(id);
    }

    @Override
    public Mono<Image> save(Image image) {
        return imageRepository.save(image);
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
