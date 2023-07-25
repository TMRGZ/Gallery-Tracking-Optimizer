package com.rviewer.skeletons.domain.sorter.factory.impl;

import com.rviewer.skeletons.domain.sorter.algorithm.GenericImageSorterAlgorithm;
import com.rviewer.skeletons.domain.sorter.factory.SorterFactory;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class SorterFactoryImpl implements SorterFactory {

    private final List<GenericImageSorterAlgorithm> imageSorterList;

    private final GenericImageSorterAlgorithm defaultSorter;

    @Override
    public GenericImageSorterAlgorithm getSorter() {
        return defaultSorter;
    }

    @Override
    public GenericImageSorterAlgorithm getSorter(String name) {
        return imageSorterList.stream()
                .filter(imageSorter -> imageSorter.getImageSorterName().equalsIgnoreCase(name))
                .findAny().orElseThrow();
    }
}
