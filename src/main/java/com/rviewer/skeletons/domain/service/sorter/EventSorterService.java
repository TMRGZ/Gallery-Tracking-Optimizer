package com.rviewer.skeletons.domain.service.sorter;

import com.rviewer.skeletons.domain.algorithm.ImageSorterAlgorithm;
import com.rviewer.skeletons.domain.model.Image;
import com.rviewer.skeletons.domain.repository.ImageRepository;
import com.rviewer.skeletons.domain.service.sorter.common.AbstractSorterService;
import reactor.core.publisher.Flux;

import java.math.BigDecimal;


public class EventSorterService extends AbstractSorterService {

    private final BigDecimal viewRating;

    private final BigDecimal clickRating;

    public EventSorterService(ImageRepository imageRepository,
                              ImageSorterAlgorithm imageSorterAlgorithm,
                              Double viewRating, Double clickRating) {
        super(imageRepository, imageSorterAlgorithm);
        this.viewRating = BigDecimal.valueOf(viewRating);
        this.clickRating = BigDecimal.valueOf(clickRating);
    }

    @Override
    public Flux<Image> prepareImages(Flux<Image> imagesToSort) {
        return super.prepareImages(imagesToSort)
                .map(this::calculateWeight);
    }

    private Image calculateWeight(Image image) {
        BigDecimal view = image.getEvents().getViews();
        BigDecimal click = image.getEvents().getClicks();

        BigDecimal weight = view.multiply(viewRating).add(click.multiply(clickRating));

        return image.toBuilder()
                .weight(weight)
                .build();
    }
}
