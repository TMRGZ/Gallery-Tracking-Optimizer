package com.rviewer.skeletons.domain.sorter.factory;

import com.rviewer.skeletons.domain.sorter.GenericImageSorter;

public interface SorterFactory {

    GenericImageSorter getSorter(String name);
}
