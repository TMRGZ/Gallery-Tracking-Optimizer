package com.rviewer.skeletons.domain.service.sorter;

import com.rviewer.skeletons.domain.algorithm.ImageSorterAlgorithm;
import com.rviewer.skeletons.domain.model.Image;
import com.rviewer.skeletons.domain.repository.ImageRepository;
import com.rviewer.skeletons.domain.service.sorter.common.AbstractSorterService;
import reactor.core.publisher.Flux;

import java.math.BigDecimal;
import java.util.Optional;


public class EventSorterService extends AbstractSorterService {

    private final double viewRating;

    private final double clickRating;

    public EventSorterService(ImageRepository imageRepository,
                              ImageSorterAlgorithm imageSorterAlgorithm,
                              double viewRating, double clickRating) {
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
        double view = Optional.ofNullable(image.getEvents().getViews())
                .map(BigDecimal::doubleValue).orElse(0.0);
        double click = Optional.ofNullable(image.getEvents().getClicks())
                .map(BigDecimal::doubleValue).orElse(0.0);

        double weight = (view * viewRating) + (click * clickRating);

        return image.toBuilder()
                .weight(BigDecimal.valueOf(weight))
                .build();
    }
}
