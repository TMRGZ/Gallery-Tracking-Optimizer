package com.rviewer.skeletons.domain.service.sorter.common;

import com.rviewer.skeletons.domain.algorithm.ImageSorterAlgorithm;
import com.rviewer.skeletons.domain.model.Image;
import com.rviewer.skeletons.domain.repository.ImageRepository;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.util.function.Tuple2;

import java.math.BigDecimal;

@RequiredArgsConstructor
public abstract class AbstractSorterService {

    private final ImageRepository imageRepository;

    private final ImageSorterAlgorithm imageSorterAlgorithm;

    public final Flux<Image> getSortedImages() {
        return prepareImages(imageRepository.findAll())
                .sort(imageSorterAlgorithm.thenComparing(imageSorterAlgorithm.fallbackCompare()))
                .index().map(this::getIndexedImage);
    }

    public Flux<Image> prepareImages(Flux<Image> imagesToSort) {
        return imagesToSort;
    }

    private Image getIndexedImage(Tuple2<Long, Image> indexedImage) {
        return indexedImage.getT2().toBuilder()
                .gridPosition(BigDecimal.valueOf(indexedImage.getT1()))
                .build();
    }

    public String getAlgorithmName() {
        return imageSorterAlgorithm.getAlgorithmName();
    }
}
