package com.rviewer.skeletons.domain.sorter.algorithm.impl;

import com.rviewer.skeletons.domain.model.Image;
import com.rviewer.skeletons.domain.sorter.algorithm.GenericImageSorterAlgorithm;
import com.rviewer.skeletons.infrastructure.config.sorter.algorithm.criteria.EventSorterCriteria;

import java.math.BigDecimal;
import java.util.Optional;

public class EventImageSorterAlgorithm extends GenericImageSorterAlgorithm {

    private final EventSorterCriteria eventSorterCriteria;

    public EventImageSorterAlgorithm(String imageSorterName, EventSorterCriteria criteria) {
        super(imageSorterName);
        this.eventSorterCriteria = criteria;
    }

    @Override
    public int compare(Image o1, Image o2) {
        double interactions1 = calculateInteractions(o1);
        double interactions2 = calculateInteractions(o2);

        int compareResult = eventSorterCriteria.getDirection().equalsIgnoreCase("desc")
                ? Double.compare(interactions2, interactions1)
                : Double.compare(interactions1, interactions2);

        return compareResult != 0 ? compareResult : super.compare(o1, o2);
    }

    private Double calculateInteractions(Image image) {
        BigDecimal view = Optional.ofNullable(image.getEvents().getViews()).orElse(BigDecimal.ZERO);
        BigDecimal click = Optional.ofNullable(image.getEvents().getClicks()).orElse(BigDecimal.ZERO);

        return view.doubleValue() * eventSorterCriteria.getView()
                + click.doubleValue() * eventSorterCriteria.getClick();
    }
}
