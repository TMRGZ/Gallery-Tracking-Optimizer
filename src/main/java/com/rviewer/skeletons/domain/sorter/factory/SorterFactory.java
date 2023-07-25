package com.rviewer.skeletons.domain.sorter.factory;

import com.rviewer.skeletons.domain.sorter.algorithm.GenericImageSorterAlgorithm;

public interface SorterFactory {

    GenericImageSorterAlgorithm getSorter(String name);
}
