package com.rviewer.skeletons.infrastructure.sorter.factory.impl;

import com.rviewer.skeletons.domain.sorter.GenericImageSorter;
import com.rviewer.skeletons.domain.sorter.factory.SorterFactory;
import com.rviewer.skeletons.domain.sorter.impl.DefaultImageSorter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SorterFactoryImpl implements SorterFactory {

    @Autowired
    private List<GenericImageSorter> imageSorterList;

    @Autowired
    private DefaultImageSorter defaultImageSorter;

    @Override
    public GenericImageSorter getSorter(String name) {
        return imageSorterList.stream()
                .filter(imageSorter -> imageSorter.getImageSorterName().equalsIgnoreCase(name))
                .findAny().orElse(defaultImageSorter);
    }
}
