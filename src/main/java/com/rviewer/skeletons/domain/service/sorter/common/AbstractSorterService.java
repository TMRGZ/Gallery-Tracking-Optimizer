package com.rviewer.skeletons.domain.service.sorter.common;

import com.rviewer.skeletons.domain.algorithm.ImageSorterAlgorithm;
import com.rviewer.skeletons.domain.exception.NoImagesFoundException;
import com.rviewer.skeletons.domain.model.Image;
import com.rviewer.skeletons.domain.repository.ImageRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.util.function.Tuple2;

import java.math.BigDecimal;

@Slf4j
@RequiredArgsConstructor
public abstract class AbstractSorterService {

    private final ImageRepository imageRepository;

    private final ImageSorterAlgorithm imageSorterAlgorithm;

    public final Flux<Image> getSortedImages() {
        log.info("Sorting and indexing images...");
        return prepareImages(imageRepository.findAll())
                .switchIfEmpty(Mono.error(NoImagesFoundException::new))
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
