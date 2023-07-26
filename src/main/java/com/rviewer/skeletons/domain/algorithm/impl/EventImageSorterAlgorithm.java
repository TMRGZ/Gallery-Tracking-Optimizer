package com.rviewer.skeletons.domain.algorithm.impl;

import com.rviewer.skeletons.domain.algorithm.ImageSorterAlgorithm;
import com.rviewer.skeletons.domain.model.Image;

public class EventImageSorterAlgorithm extends ImageSorterAlgorithm {

    public EventImageSorterAlgorithm(String algorithmName, String direction) {
        super(algorithmName, direction);
    }

    @Override
    public int compare(Image o1, Image o2) {
        return getDirection().equalsIgnoreCase("desc")
                ? o2.getWeight().compareTo(o1.getWeight())
                : o1.getWeight().compareTo(o2.getWeight());
    }
}
