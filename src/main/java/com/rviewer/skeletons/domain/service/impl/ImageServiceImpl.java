package com.rviewer.skeletons.domain.service.impl;

import com.rviewer.skeletons.domain.model.Image;
import com.rviewer.skeletons.domain.repository.ImageRepository;
import com.rviewer.skeletons.domain.service.ImageService;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;

@RequiredArgsConstructor
public class ImageServiceImpl implements ImageService {

    private final ImageRepository imageRepository;

    @Override
    public Flux<Image> getImages() {
        return imageRepository.findAll();
    }

    @Override
    public Flux<Image> saveImages(Flux<Image> imageFlux) {
        return imageRepository.saveAll(imageFlux);
    }
}
