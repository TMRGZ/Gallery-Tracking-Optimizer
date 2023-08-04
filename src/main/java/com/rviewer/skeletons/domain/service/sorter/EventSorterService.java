package com.rviewer.skeletons.domain.service.sorter;

import com.rviewer.skeletons.domain.algorithm.ImageSorterAlgorithm;
import com.rviewer.skeletons.domain.model.Image;
import com.rviewer.skeletons.domain.repository.ImageRepository;
import com.rviewer.skeletons.domain.service.sorter.common.AbstractSorterService;
import reactor.core.publisher.Flux;

import java.math.BigDecimal;
import java.util.Optional;


public class EventSorterService extends AbstractSorterService {

    private final Double viewRating;

    private final Double clickRating;

    public EventSorterService(ImageRepository imageRepository,
                              ImageSorterAlgorithm imageSorterAlgorithm,
                              Double viewRating, Double clickRating) {
        super(imageRepository, imageSorterAlgorithm);
        this.viewRating = viewRating;
        this.clickRating = clickRating;
    }

    @Override
    public Flux<Image> prepareImages(Flux<Image> imagesToSort) {
        return super.prepareImages(imagesToSort)
                .map(this::calculateWeight);
    }

    private Image calculateWeight(Image image) {
        double view = getDefaultCounter(image.getEvents().getViews());
        double click = getDefaultCounter(image.getEvents().getClicks());

        double weight = (view * viewRating) + (click * clickRating);

        return image.toBuilder()
                .weight(BigDecimal.valueOf(weight))
                .build();
    }

    private double getDefaultCounter(BigDecimal nullableNumber) {
        return Optional.ofNullable(nullableNumber)
                .map(BigDecimal::doubleValue).orElse(0.0);
    }
}
