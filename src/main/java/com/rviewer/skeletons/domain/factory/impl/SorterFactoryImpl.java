package com.rviewer.skeletons.domain.factory.impl;

import com.rviewer.skeletons.domain.factory.SorterFactory;
import com.rviewer.skeletons.domain.service.sorter.common.AbstractSorterService;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class SorterFactoryImpl implements SorterFactory {

    private final List<AbstractSorterService> imageSorterList;

    private final String defaultSorter;

    @Override
    public AbstractSorterService getSorter(String name) {
        return imageSorterList.stream()
                .filter(imageSorter -> imageSorter.getAlgorithmName().equalsIgnoreCase(name))
                .findAny()
                .orElseGet(() -> getSorter(defaultSorter));
    }
}
