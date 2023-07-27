package com.rviewer.skeletons.domain.algorithm.impl;

import com.rviewer.skeletons.domain.algorithm.ImageSorterAlgorithm;
import com.rviewer.skeletons.domain.model.Image;
import com.rviewer.skeletons.domain.model.enums.SortDirectionEnum;

public class EventImageSorterAlgorithm extends ImageSorterAlgorithm {

    public EventImageSorterAlgorithm(String algorithmName, SortDirectionEnum direction) {
        super(algorithmName, direction);
    }

    @Override
    public int compare(Image o1, Image o2) {
        return getDirection() == SortDirectionEnum.DESC
                ? o2.getWeight().compareTo(o1.getWeight())
                : o1.getWeight().compareTo(o2.getWeight());
    }
}
