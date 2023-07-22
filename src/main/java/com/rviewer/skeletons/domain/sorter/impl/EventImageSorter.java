package com.rviewer.skeletons.domain.sorter.impl;

import com.rviewer.skeletons.domain.model.Image;
import com.rviewer.skeletons.domain.sorter.GenericImageSorter;


public class EventImageSorter extends GenericImageSorter {
    public EventImageSorter(String imageSorterName) {
        super(imageSorterName);
    }

    @Override
    public int compare(Image o1, Image o2) {
        return 0;
    }
}
